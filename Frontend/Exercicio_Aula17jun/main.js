class FizzBuzz {
    constructor(loopCount) {
        this.loopCount = loopCount;
    }

    resultado() {
        // Iniciar arrays e variaveis
        let mult3 = [];
        let mult5 = [];
        let mult35 = [];
        let i = 0;
        let contCell = 0;
        let table = document.getElementById("tableBody");
        let tableRow = document.createElement("tr");

        // Limpar table para não ter objetos repetidos
        table.innerHTML = "";

        // Utilizando ciclo while    
        while (i <= this.loopCount) {
            let tableCell;
            
            if (i % 3 == 0 && i % 5 == 0) {
                mult35.push(" " + i);
                tableCell = document.createElement("td");
                tableCell.textContent = i + " <FizzBuzz>";
            } else if (i % 3 == 0) {
                mult3.push(" " + i);
                tableCell = document.createElement("td");
                tableCell.textContent = i + " <Fizz>";
            } else if (i % 5 == 0) {
                mult5.push(" " + i);
                tableCell = document.createElement("td");
                tableCell.textContent = i + " <Buzz>";
            }

            // Apenas adiciona uma Cell se uma foi criada anteriormente (se encontrou um nº divisivel por 3 ou 5)
            if (tableCell) {
                tableRow.appendChild(tableCell);
                contCell++;
            }

            // Se já foram adicionados 5 elementos a uma Row, fazer reset para criar uma nova Row
            if (contCell == 5) {
                table.appendChild(tableRow);
                tableRow = document.createElement("tr");
                contCell = 0;
            }
            
            // Incrementar o index
            i++;
        }

        // Se a Row não possuir 5 cells (ficar cheia), adiciona a Cell na mesma
        if (contCell > 0) {
            table.appendChild(tableRow);
        }

        document.getElementById("textAreaResult").value = 
            "Multiplos de 3: " + mult3 + "\n\n" +
            "Multiplos de 5: " + mult5 + "\n\n" +
            "Multiplos de 3 e de 5: " + mult35;
    }
}

function invokeResultado() {
    new FizzBuzz(document.getElementById("numberInput").value).resultado();
}

// Utilizando ciclo for
// for (let i = 0; i <= 40; i++) {
//     if (i % 3 == 0 && i % 5 == 0) {
//         mult35.push(i);
//         console.log("Mult. de 3 e 5: " + i + " ==> FizzBuzz");
//     }
//     else if (i % 3 == 0) {
//         mult3.push(i);
//         console.log("Mult. de 3: " + i + " ==> Fizz");
//     }
    
//     else if (i % 5 == 0) {
//         mult5.push(i);
//         console.log("Mult. de 5: " + i + " ==> Buzz");
//     }
// }
