const dados = [
    {
        client: "Zé Carlos",
        products: [
            {
                product: "Atum",
                iva: 13,
                preco: 4
            }, {
                product: "Coca-Cola",
                iva: 23,
                preco: 2
            }, {
                product: "Agua",
                iva: 6,
                preco: 1
            }
        ]
    }, {
        client: "Zé Manel",
        products: [{
            product: "Bolachas",
            iva: 23,
            preco: 3
        }, {
            product: "Coca-Cola",
            iva: 23,
            preco: 2
        }]
    }]
class DesafioIva {
    constructor(dados) {
        // Buscar o Array original e copiá-lo para não o modificar
        this.arrayTemp = [...dados];
    }
    calcular() {
        let tbodyDados = document.getElementById("tabelaDadosProdutos");
        let tbodyTotais = document.getElementById("tabelaDadosTotais");
        
        // Limpar as tabelas para não colocar elementos repetidos
        tbodyDados.innerHTML = "";
        tbodyTotais.innerHTML = "";
        
        // Correr o Array temporario (para não alterar o original)
        this.arrayTemp.forEach(dadosCliente => {
            let precoCliente = 0;
            // Para cada produto existente na lista do Cliente..
            dadosCliente.products.forEach(produto => {  
                let precoComIva = produto.preco + (produto.preco * (produto.iva / 100)); // Calcular o preço com IVA do produto        
                precoCliente += precoComIva;

                // Criar tabela com os dados dos Clientes e dos Produtos
                tbodyDados.innerHTML += `
                    <tr>
                        <td>${dadosCliente.client}</td>
                        <td>${produto.product}</td>
                        <td>${produto.preco.toFixed(2)}€</td>
                        <td>${produto.iva}%</td>
                        <td>${precoComIva.toFixed(2)}€</td>
                    <tr>
                `
            })
            tbodyTotais.innerHTML += `
                    <tr>
                        <td>${dadosCliente.client}</td>                    
                        <td>${precoCliente.toFixed(2)}€</td>
                    <tr>
                `
        })
    }
}

function mostrar() {
    new DesafioIva(dados).calcular();
    // Remover o "d-none" que impede que o cabeçalho das Tabelas não seja mostrado
    document.getElementById("thDados").classList.remove("d-none");
    document.getElementById("thTotais").classList.remove("d-none");
}