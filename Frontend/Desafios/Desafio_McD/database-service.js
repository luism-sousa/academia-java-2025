import { pedidos, pedidosConcluidos } from './main.js';

export function guardarLocalStorage() {
    localStorage.setItem("pedidos", JSON.stringify(pedidos));
    localStorage.setItem("pedidosConcluidos", JSON.stringify(pedidosConcluidos));
}

export function checkLocalStorage() {
    const dadosPedidos = localStorage.getItem("pedidos");
    const dadosPedidosConcluidos = localStorage.getItem("pedidosConcluidos");

    if (dadosPedidos) {
        const json = JSON.parse(dadosPedidos);
        pedidos = json.map(p => new Pedido(p.id, p.cliente, p.produtos.map(prod => new ProdutoPedido(prod.produto, prod.preco, prod.extra))));
    }

    if (dadosPedidosConcluidos) {
        const json = JSON.parse(dadosPedidosConcluidos);
        pedidosConcluidos = json.map(p => new Pedido(p.id, p.cliente, p.produtos.map(prod => new ProdutoPedido(prod.produto, prod.preco, prod.extra))));
    }
}