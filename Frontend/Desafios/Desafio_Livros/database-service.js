import { booksUserLiked, Books } from './main.js';

export function guardarLocalStorage() {
    localStorage.setItem("booksUserLiked", JSON.stringify(booksUserLiked));
}

export function checkLocalStorage() {
    const dadosBooksUserLiked = localStorage.getItem("booksUserLiked");

    if (dadosBooksUserLiked) {
        const json = JSON.parse(dadosBooksUserLiked);
        json.forEach(b => booksUserLiked.push(
            new Books(b.bookCover, b.title, b.description, b.authors, b.publisher, b.url)
        ));
    }
}