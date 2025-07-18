import { checkLocalStorage, guardarLocalStorage } from './database-service.js';

export class Books {
    constructor(bookCover, title, description, authors, publisher, url) {
        this.bookCover = bookCover;
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.publisher = publisher;
        this.url = url;
    }
}

// Simplificar texto da descrição do Livro
function simplifyBookDescription(text, maxLength) {
    if (!text) {
        return "";
    }
    return text.length > maxLength ? text.slice(0, maxLength) + ".." : text;
}

// Array com todos os livros oriundos da Google API
let allBooks = [];
// Array com os livros visiveis na lista de Livros
let currentBooks = [];
// Array com os livros "gostados"
export let booksUserLiked = [];

let nextBookPointer = 0;        // Index para o proximo Book a ser mostrado a partir do array "allBooks"
let currentStartIndex = 0;      // Index inicial para a Google API
let totalItems = 0;             // Nº total de livros encontrados pela Google API numa pesquisa
const maxResults = 40;          // Variavel que guarda o nº máximo de livros por pedido à Google API
const maxBooksPerPage = 6;      // Variavel que guarda o nº máximo de livros (de cada vez) a serem mostrados no ecrã

function getBooksFromGoogle(search, startIndex = 0) {
    const url = `https://www.googleapis.com/books/v1/volumes?q=${search}&maxResults=${maxResults}&startIndex=${startIndex}`

    fetch(url)
        .then((response) => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Something went wrong');
        })
        .then((responseJson) => {
            // Se for uma nova pesquisa..
            if (startIndex == 0) {
                allBooks = [];  // Limpar os resultados anteriores..
                totalItems = responseJson.totalItems;   // Atualizar o nº total de livros encontrados
            }
            const newBooks = responseJson.items.map(book => {
                const info = book.volumeInfo;
                return new Books(
                    info.imageLinks && info.imageLinks.thumbnail
                        ? info.imageLinks.thumbnail
                        : "",
                    info.title,
                    info.description,
                    info.authors ? info.authors.join(", ") : "Unknown",
                    info.publisher || "Unknown",
                    info.infoLink || "#"
                );
            }) || []; // Retorna um array vazio se a resposta for null
            allBooks = allBooks.concat(newBooks);
            if (startIndex == 0) {
                currentBooks = allBooks.slice(0, 6);
                nextBookPointer = maxBooksPerPage;
            }
            gerarCardsLivros(currentBooks);
        })
        .catch((error) => {
            console.log(error)
        });
}

// Função que obtem mais livros da Google API quando se atinge o nº máximo de livros obtidos mostrados no ecrã
// (visto que são "removidos" caso o User goste/não goste dos livros)
function obterMaisLivros(search) {
    if (allBooks <= nextBookPointer) {
        currentStartIndex += maxResults;
        getBooksFromGoogle(search, currentStartIndex);
    }
}

// == Criação de cards ==
// Função para gerar as Cards (livros) que vão ser apresentados
function gerarCardsLivros(listaLivros) {
    const toastAlert = new Notyf({ position: { x: 'center', y: 'top' } }); // Variavel para o toast (usando a Biblioteca "Notyf")
    let divContainer = document.getElementById("booksCards");
    divContainer.innerHTML = "";

    // Para cada livro na lista de Livros a apresentar..
    listaLivros.forEach((livro, index) => {
        // Criação de coluna para o card
        let auxColumn = document.createElement("div");
        auxColumn.innerHTML = `
            <div class="card h-100">
                <img src="${livro.bookCover}" class="card-img-top fixed-img" alt="${livro.title}">
                <div class="card-body">
                    <h5 class="card-title fw-bold text-center">${livro.title}</h5>
                    <p class="card-text fw-light text-center">${simplifyBookDescription(livro.description, 150)}</p>
                    <p class="card-text text-center"><b>Author(s):</b> ${livro.authors}</p>
                    <p class="card-text text-center"><b>Publisher:</b> ${livro.publisher}</p>
                    <a href=${livro.url} target="_blank" class="stretched-link"></a>
                </div>
                <div class="d-flex justify-content-around position-relative mb-4" style="z-index: 1">
                        <i class="fa-solid fa-heart fa-2xl m-3 likedBook" role="button" style="color: #ff8080;"></i>
                        <i class="fa-solid fa-xmark fa-2xl m-3 didntLikedBook" role="button"></i>
                </div>
            </div>
        `;

        divContainer.appendChild(auxColumn);

        // Ação dos botões "Gosto" / "Não Gosto"
        auxColumn.querySelector(".likedBook").addEventListener("click", () => {
            // Verificar se o livro que foi "Gostado" já existe na lista de favoritos
            //
            // Array.some =>  It returns true if, in the array, it finds an element for which the provided function returns 
            // true; otherwise it returns false. It doesn't modify the array.
            //          
            const alreadyLikedBook = booksUserLiked.some(b => b.title == livro.title);
            if (alreadyLikedBook == false) {
                booksUserLiked.push(livro);
                guardarLocalStorage();                
            } else {
                toastAlert.error("This books already exists in your 'Favourite Shelf!'");
            }
            changeVisibleBooks(index);
        });

        auxColumn.querySelector(".didntLikedBook").addEventListener("click", () => {
            changeVisibleBooks(index);
        });
    });
}
// == Criação de cards ==

// Função para mudar os livros sempre que o User carrega no botão "Gosto" ou "Não gosto"
function changeVisibleBooks(index) {
    if (nextBookPointer < allBooks.length) {
        currentBooks[index] = allBooks[nextBookPointer];
        nextBookPointer++;
        gerarCardsLivros(currentBooks);
    } else if (allBooks.length < totalItems) {
        getBooksFromGoogle(document.getElementById("bookSearchInput" || "javascript"), allBooks.length);
    } else {
        currentBooks.splice(index, 1);
        gerarCardsLivros(currentBooks);
    }
}

// Função para mostrar os livros gostados na página "likedBooks"
export function showLikedBooks() {
    const tableContent = document.getElementById("likedBooksTable");

    if (!tableContent)
        return;

    tableContent.innerHTML = "";

    if (booksUserLiked.length == 0) {
        tableContent.innerHTML = `<tr><td colspan="6" class="text-center">No liked books saved.</td></tr>`;
        return;
    }

    booksUserLiked.forEach((livro, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td class="align-middle fw-semibold">${livro.title}</td>
            <td class="align-middle">${simplifyBookDescription(livro.description, 150)}</td>
            <td class="align-middle">${livro.authors}</td>
            <td class="align-middle">${livro.publisher}</td>
            <td class="align-middle"><a href="${livro.url}" target="_blank">View book</a></td>
            <td class="align-middle text-center">
                <button class="btn btn-danger btn-sm remove-liked-book" data-index="${index}">
                    <i class="fa-solid fa-trash"></i>
                </button>
            </td>
        `;
        tableContent.appendChild(row);
    });

    // Botão "Remover" livro favorito
    tableContent.querySelectorAll('.remove-liked-book').forEach(btn => {
        btn.addEventListener('click', function () {
            const index = this.getAttribute('data-index');
            booksUserLiked.splice(index, 1);
            localStorage.setItem("booksUserLiked", JSON.stringify(booksUserLiked));
            showLikedBooks(); // Refresh table
        });
    });
}

document.addEventListener("DOMContentLoaded", () => {
    checkLocalStorage();
    if (document.getElementById("booksCards")) {
        // Pesquisar livros na caixa de Input
        const bookSearchInput = document.getElementById("bookSearchInput");

        // Inicializar a lista com livros
        getBooksFromGoogle(bookSearchInput.value || 'javascript');

        // Timer de 1 segundo para evitar que os livros sejam mudados imediatamente após o User
        // pesquisar algum livro, autor, ...
        let timerLivros;

        bookSearchInput.addEventListener("input", () => {
            clearTimeout(timerLivros);
            timerLivros = setTimeout(() => {
                if (bookSearchInput.value.length > 0)
                    getBooksFromGoogle(bookSearchInput.value);
                else
                    getBooksFromGoogle("javascript");
            }, 1000);
        });
    }

    if (document.getElementById("likedBooksTable")) {
        showLikedBooks();
    }
});

