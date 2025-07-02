let data = [1, "2", [20, [4, "p12", [6, "l30"], 3], ["5", "9"], 10], 8, 7];

document.getElementById("originalArray").innerHTML = "Array original: " + JSON.stringify(data);
// Função recursiva para dar flat ao array
function flatAndConvert(arr) {
    return arr.flatMap(item => {
        if (Array.isArray(item)) {
            return flatAndConvert(item);
        } else {
            let num = Number(item);
            return isNaN(num) ? (item.match(/\d+/g)).map(Number) : [num];
        }
    });
}

function mostrar() {
    let flattened = flatAndConvert(data);
    let sorted = flattened.sort((a, b) => a - b);
    // Saída esperada: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 20, 30]
    document.getElementById("result").innerHTML = "Array ordenado: " + sorted;
}
