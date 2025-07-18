// *
// Biblioteca usada para construir tabelas dinamicamente
// *
class MyTable {
  helperArrayToHTMLElement(a) {
      let elementosArray = '';
      for (let index = 0; index < a.length; index++) {
        elementosArray += `<th>${a[index]}</th>`
      }
      return elementosArray;
  }
  
  initTable(tableId, hNames) {
    let headElements = this.helperArrayToHTMLElement(hNames);
    document.getElementById(tableId).innerHTML = `
     <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          ${headElements}
        </tr>
      </thead>
      <tbody class="table-group-divider" id="table1_body">
        <tr>
        </tr>    
      </tbody>
     </table>
    `
  }

  /**
   * 
   * @param {string} tableBodyId - Id da tabela que quero adicionar
   * @param {Array} a -  Array de dados que a tabela irá receber
   */
  addArrayToTableRow(tableBodyId, a) {
    document.getElementById(tableBodyId).insertAdjacentHTML('beforeend', `
      <tr>
        <th scope="row">${document.getElementById("table1_body").rows.length}</th>
        ${this.helperArrayToHTMLElement(a, 'td')}
      </tr>`);
  }
}

// const table1Data = [];
// let table1 = new MyTable();
// table1.initTable("tabelaPrincipal", ["nome", "idade"]);
// table1.addArrayToTableRow("table1_body", ["Luis", 30]);
// table1.addArrayToTableRow("table1_body", ["Pedro", 28]);
