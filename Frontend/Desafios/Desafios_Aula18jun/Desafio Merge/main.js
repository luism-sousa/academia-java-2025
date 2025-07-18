const data1 = [{
      name: "Ze Carlos",
      id: 5
    }, {
      name: "Ze Manel",
      id: 6
    }, {
      name: "Sam",
      id: 4
    }]

const data2 = [{
      name: "Gata",
      id: 3
    }, {
      name: "Sam",
      id: 4
}]

const data3 = [{
      name: "Ze Carlos",
      id: 5
    }, {
      name: "Hulk",
      id: 1
    }, {
      name: "Migas",
      id: 2
}]

function mostrar() {
    let allData = [...data1, ...data2, ...data3];
    // >> Resolução sem biblioteca dinámica <<
    //
    // document.getElementById('result').innerText = result.toString();

    // Adição deste items utilizando a biblioteca de criação de Tabelas Dinamicas (exercicio 24/jun)
    // let orderedData = [...new Set(allData.map(el => el.name))].sort();
    
    // let table1 = new MyTable();
    // table1.initTable("tabelaPrincipal", Object.keys(allData[0]));

    // for (let i = 0; i < allData.length; i++) {
    //   let elementosArray = allData[i];
    //   table1.addArrayToTableRow("table1_body", Object.values(result));
    // }
    //
    // >> Resolução sem biblioteca dinámica <<

    // Adição deste items utilizando a biblioteca de criação de Tabelas Dinamicas (Exercicio 24/jun)
    const uniqueNames = [...new Set(allData.map(el => el.name))].sort();

    // Para cada "nome" único, find the first matching object to get the ID
    const uniqueData = uniqueNames.map(name => {
      return allData.find(el => el.name === name);
    });

    // Dynamically get columns from object keys
    let table1 = new MyTable();
    table1.initTable("tabelaPrincipal", Object.keys(uniqueData[0]));

    // Add each row with name and ID
    uniqueData.forEach(entry => {
      table1.addArrayToTableRow("table1_body", Object.values(entry));
    });
}