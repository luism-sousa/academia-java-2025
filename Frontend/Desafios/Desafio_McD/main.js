import {
    productNameSanduichesList,
    productNameAcompanhamentosList,
    productNameBebidasList,
    productExtraSanduichesList,
    productExtraBebidasList

} from "./sampleData.js";

import { guardarLocalStorage } from './database-service.js';

class Pedido {
    constructor(id, cliente, produtos) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
    }
    toHTML() {
        return this.produtos.map(p => `<li>${p.toString()}</li>`).join('');
    }
    temAlgumExtra() {
        return this.produtos.some(p => p.temExtra());
    }
}

class ProdutoPedido {
    constructor(produto, preco, extra) {
        this.produto = produto;
        this.preco = preco;
        this.extra = extra;
    }
    temExtra() {
        return this.extra && this.extra.trim() !== '';
    }
    toString() {
        return `${this.produto} - ${this.preco}${this.temExtra() ? ' (Extra: ' + this.extra + ')' : ''}`;
    }
}

// Array com os produtos adicionados ao "Resumo de Pedidos"
let produtosTemp = [];

// Função para criar um ID usando o nome do produto com letras minusculas, removendo simbolos ou espaços
function createId(str) {
    return str.toLowerCase().replace(/[^a-z0-9]/g, '').trim();
}

// Opções para os extras dependendo se são Hamburgueres ou Bebidas (Acompanhamentos não têm)
let extraPorTipo = {
    "#hamburgueres": productExtraSanduichesList.map(extra => ({
        id: createId(extra), label: extra
    })),
    "#bebidas": productExtraBebidasList.map(extra => ({
        id: createId(extra), label: extra
    }))
}

// Função para mostrar e mudar Checkboxes (extras) dependendo da Tab selecionada
function alterarPedidosEspeciais(tabId) {
    let cbExtra = document.getElementById("cbExtra");
    cbExtra.innerHTML = "";
    let opcoesExtra = extraPorTipo[tabId] || [];

    opcoesExtra.forEach(extra => {
        let divOpcoes = document.createElement("div");
        divOpcoes.className = "form-check";
        divOpcoes.innerHTML = `
            <input class="form-check-input" type="checkbox" value="" id="checkbox-${extra.id}">
            <label class="form-check-label" for="checkbox-${extra.id}">
                ${extra.label}
            </label>
        `;
        cbExtra.appendChild(divOpcoes);
    });
}

// Função para obter extras selecionados
function obterExtrasSelecionados() {
    let cbSelec = document.querySelectorAll('#cbExtra input[type="checkbox"]');
    let extrasSelec = [];

    cbSelec.forEach(checkbox => {
        if (checkbox.checked) {
            let lblCheckbox = document.querySelector(`label[for=${checkbox.id}`);
            extrasSelec.push(lblCheckbox.textContent.trim());
        }
    });
    return extrasSelec;
}

// Função para limpar extras selecionados
function limparExtrasSelecionados() {
    let cbSelec = document.querySelectorAll('#cbExtra input[type="checkbox"]');
    cbSelec.forEach(checkbox => {
        checkbox.checked = false;
    });
}

// == Criação de cards ==
// Função para obter a localização da imagem
function getImagePath(tipo, nomeProduto) {
    const imgName = createId(nomeProduto) + ".jpg";
    switch (tipo) {
        case "hamburgueres":
            return `../Desafio_McD/img/Hamburgueres/${imgName}`;
        case "bebidas":
            return `../Desafio_McD/img/Bebidas/${imgName}`;
        case "acompanhamentos":
            return `../Desafio_McD/img/Acompanhamentos/${imgName}`;
        default:
            return "";
    }
}

// Função para adicionar os produtos a tabela "Resumo do Pedido"
function adicionarProduto(prod) {
    let produtoSelecionado = prod.closest(".card");
    let nomeProduto = produtoSelecionado.querySelector(".card-title").innerText;
    let precoProduto = produtoSelecionado.querySelector(".card-text.fw-light.text-center").innerText;

    // Buscar ao Array "extrasSelec" todos os extras selecionados
    let listaExtras = obterExtrasSelecionados().join(", ");

    // Adicionar items a tabela
    let tabelaProdutos = document.getElementById("tabelaResumoPedido").querySelector("tbody");
    let novaLinhaTab = document.createElement("tr")
    novaLinhaTab.dataset.nomeProduto = nomeProduto;

    novaLinhaTab.innerHTML = `
        <td><button class="btn btn-danger remover-produto">Remover</button></td>
        <td>${nomeProduto}</td>
        <td>${precoProduto}</td>
        <td>${listaExtras}</td>
    `;

    produtosTemp.push(new ProdutoPedido(nomeProduto, precoProduto, listaExtras));

    // Remover o produto da tabela se o botão "Remover" for pressionado
    novaLinhaTab.querySelector(".remover-produto").addEventListener("click", (e) => {
        const linha = e.target.closest("tr");
        const nomeProduto = linha.dataset.nomeProduto;

        linha.remove();
        const index = produtosTemp.findIndex(p => p.produto === nomeProduto);
        if (index > -1) {
            produtosTemp.splice(index, 1);
        }
        console.log("produtosTemp after remove:", produtosTemp);
    });

    tabelaProdutos.appendChild(novaLinhaTab);
    limparExtrasSelecionados();
    console.log("produtosTemp after push:", produtosTemp);
}

// Função para gerar as Cards (produtos) que vão ser apresentados
function gerarCards(listaProdutos, divContainerId, tipo) {
    let divContainer = document.getElementById(divContainerId);
    // Limpar a variável
    divContainer.innerHTML = "";

    // Para cada produto na lista de Produtos a apresentar..
    listaProdutos.forEach(produto => {
        // Criação de coluna para o card
        let auxColumn = document.createElement("div");
        auxColumn.innerHTML = `
            <div class="card h-100">
                <img src="${getImagePath(tipo, produto.name)}" class="card-img-top fixed-img" alt="${produto.name}">
                <div class="card-body">
                    <h5 class="card-title fw-bold">${produto.name}</h5>
                    <p class="card-text fw-light text-center">${produto.price}</p>
                    <a class="stretched-link"></a>
                </div>
            </div>
        `;

        const link = auxColumn.querySelector(".stretched-link");
        link.addEventListener("click", (e) => {
            e.preventDefault();
            adicionarProduto(e.currentTarget);
        });
        divContainer.appendChild(auxColumn);
    });
}
// == Criação de cards ==

// == Pedidos Ativos/Concluidos ==
export let pedidos = [];
export let pedidosConcluidos = [];

function marcarPedidoPreparado(id) {
    const index = pedidos.findIndex(p => p.id === id);
    if (index !== -1) {
        pedidosConcluidos.push(pedidos[index]);
        console.log("concluidos => ", pedidosConcluidos);
        pedidos.splice(index, 1);
        atualizarTabela();
        guardarLocalStorage();
        criarConcluido();
    }
}

// Adiciona o pedido a tabela de Pedidos Concluidos
function criarConcluido() {
    const tbody = document.getElementById("pedidosConcluidos");
    tbody.innerHTML = ""; // Limpar linhas

    pedidosConcluidos.forEach(pedido => {
        const produtosHtml = pedido.produtos.map(prod => {
            return `<div>${prod.produto}</div>`;
        }).join("");

        const extrasHtml = pedido.produtos.map(prod => {
            return `<div>${prod.temExtra() ? prod.extra : `&nbsp;`}</div>`
        }).join("");

        // Calcular preço total
        const totalPreco = pedido.produtos.reduce((acc, prod) => {
            const precoNumerico = parseFloat(prod.preco.replace(/[^\d.-]/g, ''));
            return acc + precoNumerico;
        }, 0);

        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${pedido.id}</td>
            <td>${pedido.cliente}</td>
            <td>${produtosHtml}</td>
            <td>${totalPreco}€</td>
            <td>${extrasHtml}</td>
        `;
        tbody.appendChild(row);
    });
}

function removerPedidoAtivo(id) {
    const index = pedidos.findIndex(p => p.id === id);
    if (index !== -1) {
        pedidos.splice(index, 1);
        atualizarTabela();
        guardarLocalStorage();
    }
}

function atualizarTabela() {
    const tbody = document.getElementById("tabelaPedidosAtivos");
    tbody.innerHTML = ""; // Limpar linhas

    pedidos.forEach(pedido => {
        const produtosHtml = pedido.produtos.map(prod => {
            return `<div>${prod.produto}</div>`;
        }).join("");

        const extrasHtml = pedido.produtos.map(prod => {
            // return `<div>${prod.extra}</div>`;
            return `<div>${prod.temExtra() ? prod.extra : `&nbsp;`}</div>`
        }).join("");

        // Calcular preço total
        const totalPreco = pedido.produtos.reduce((acc, prod) => {
            const precoNumerico = parseFloat(prod.preco.replace(/[^\d.-]/g, ''));
            return acc + precoNumerico;
        }, 0);

        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${pedido.id}</td>
            <td>${pedido.cliente}</td>
            <td>${produtosHtml}</td>
            <td>${totalPreco.toFixed(2)}€</td>
            <td>${extrasHtml}</td>
            <td>
                <div class="d-flex gap-2">
                    <button class="btn btn-success btn-sm">Pedido preparado</button>
                    <button class="btn btn-danger btn-sm">Remover</button>
                </div>
            </td>
        `;

        // Botões "Pedido preparado" / "Remover"
        const btnPreparado = row.querySelector(".btn-success");
        const btnRemover = row.querySelector(".btn-danger");
        btnPreparado.addEventListener("click", () => marcarPedidoPreparado(pedido.id));
        btnRemover.addEventListener("click", () => removerPedidoAtivo(pedido.id));
        tbody.appendChild(row);
    });
}

function criarPedido() {
    let cliente = document.getElementById("nomeCliente");
    const btnFinalizarPedido = document.getElementById("btnFinalizarPedido");

    btnFinalizarPedido.addEventListener("click", () => {
        // Caso o nome do Cliente ou a tabela de Pedidos esteja vazio, mostrar erro
        if (cliente.value == "") {
            return alert("Nome de Cliente vazio!");
        }

        if (produtosTemp.length == 0) {
            return alert("Tabela de pedidos vazia!");
        }

        // Criar novo pedido e colocar a informação no Array de pedidos
        let novoPedido = new Pedido(Date.now().toString().slice(-6), cliente.value, produtosTemp);
        pedidos.push(novoPedido);

        // Limpar variaveis para o próximo pedido e Atualizar a tabela de Pedidos
        produtosTemp = [];
        document.getElementById("nomeCliente").value = "";
        document.getElementById("listaProdutosTemp").innerHTML = ``;

        console.log("pedidos after push:", pedidos);

        guardarLocalStorage();
        atualizarTabela();
        document.querySelector("#tabelaResumoPedido tbody").innerHTML = "";
    });
}
// == Pedidos Ativos/Concluidos ==


// Sempre que o website for carregado..
document.addEventListener("DOMContentLoaded", () => {
    let botoesTabs = document.querySelectorAll('button[data-bs-toggle="tab"]');
    let lblPedidosEsp = document.getElementById("cbLabel");

    gerarCards(productNameSanduichesList, "sanduichesCards", "hamburgueres");
    gerarCards(productNameBebidasList, "bebidasCards", "bebidas");
    gerarCards(productNameAcompanhamentosList, "acompanhamentosCards", "acompanhamentos");

    botoesTabs.forEach(btn => {
        btn.addEventListener("shown.bs.tab", (e) => {
            let tabSelec = e.target.getAttribute("data-bs-target");
            alterarPedidosEspeciais(tabSelec);
            // Esconder a label "Pedidos Especiais" para a tab "Acompanhamentos"
            return (tabSelec === "#acompanhamentos") ? lblPedidosEsp.style.display = "none" : lblPedidosEsp.style.display = "block";
        });
    });

    // Chamar o método "alterarPedidosEspeciais" para carregar as checkboxes por default
    let tabDefault = document.querySelector('button[data-bs-toggle="tab"].active');
    alterarPedidosEspeciais(tabDefault.getAttribute("data-bs-target"));

    criarPedido();
});
