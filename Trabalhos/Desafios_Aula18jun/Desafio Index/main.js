const numbers = [12, 3, 4, 5, 6, 7, 12, 12, 7, 8];
class DesafioIndex {
    constructor(numbers) {
        this.arrayTemp = [...numbers];
    }

    showArray() {
        let linhaIndex = document.getElementById("linhaIndex");
        let linhaNumeros = document.getElementById("linhaNumeros");

        // Limpar as tabelas para não colocar elementos repetidos
        linhaIndex.innerHTML = "";
        linhaNumeros.innerHTML = "";

        // Criar Row para os Index ([0], [1], [2], ..)
        let criarLinhaIndex = document.createElement("tr");
        this.arrayTemp.forEach((_value, index) => {
            let indexTd = document.createElement("td");
            indexTd.textContent = `[${index}]`;
            criarLinhaIndex.appendChild(indexTd);
        });
        linhaIndex.appendChild(criarLinhaIndex);

        // Criar Row para os números (12, 3, 4, ..)
        let criarLinhaNum = document.createElement("tr");
        this.arrayTemp.forEach((value) => {
            let valueTd = document.createElement("td");
            valueTd.textContent = value;
            criarLinhaNum.appendChild(valueTd);
        });
        linhaNumeros.appendChild(criarLinhaNum);
    }

    mostFrequentNum(arr) {         
        let frequencia = [];// Guardar a frequencia de cada número do Array 'numbers'
        let maxContador = 0;
        let topFrequente;

        // Ciclo para verificar o número mais frequente do Array
        for (let num of arr) {
            frequencia[num] = (frequencia[num] || 0) + 1;
            if (frequencia[num] > maxContador) {
                maxContador = frequencia[num];
                topFrequente = num;
            }            
        }        

        // Depois de ter o número mais frequente do Array, verificar as posições onde o nº se encontra
        let indexTopFrequente = [];
        arr.forEach((value, index) => {
            if (value === topFrequente) {
                indexTopFrequente.push(index);
            }
        });

        return { numero: topFrequente, indice: indexTopFrequente};
    }
}

function mostrar() {
    new DesafioIndex(numbers).showArray();
    let resultado = new DesafioIndex(numbers).mostFrequentNum(numbers);
    document.getElementById("resultadoMF").innerHTML = `Número mais frequente: ${resultado.numero}<br> 
    Posição: ${resultado.indice.join(", ")}`;
}