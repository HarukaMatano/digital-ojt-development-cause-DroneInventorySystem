const apiUrl = 'http://localhost:8080/stock-info';
const categoryApiUrl = 'http://localhost:8080/category-info';
const nameApiUrl = 'http://localhost:8080/stock-info/search';

const stockList = document.getElementById('stock-list');

// Enable or disable logging
const enableLogging = true;

// Function to log messages to the console
function logMessage(message) {
    if (enableLogging) {
        console.log(message);
    }
}

// 検索フォームの枠を生成
const searchContainer = createSearchContainer();
stockList.appendChild(searchContainer);

// 分類名をAPIから取得してプルダウンメニューに表示
fetchData(categoryApiUrl, populateCategorySelect);

// 全ての名称を保持する変数
let allNames = [];

// 初期データを取得して表示
fetchData(`${apiUrl}/active`, displayStockInfo);

// 分類名が選択されたときに名称のプルダウンメニューを更新
document.getElementById('category-select').addEventListener('change', handleCategoryChange);

// 検索ボタン押下後の動作を追加
document.querySelector('.btn-primary').addEventListener('click', handleSearch);

function createSearchContainer() {
    const container = document.createElement('div');
    container.classList.add('search-container');
    container.style.display = 'flex';
    container.style.flexWrap = 'wrap';

    container.appendChild(createSelect('category-select', '分類を選択'));
    container.appendChild(createSelect('name-select', '名称を選択'));
    container.appendChild(createInput('number', '個数を入力'));
    container.appendChild(createSelect('amount-condition-select', '以上・以下を選択', ['以上', '以下']));
    container.appendChild(createButton('検索', 'btn btn-primary'));

    return container;
}

function createSelect(id, defaultText, options = []) {
    const select = document.createElement('select');
    select.id = id;
    select.classList.add('form-control', 'mr-2');
    select.style.flex = '1';
    select.style.minWidth = '150px';
    select.style.marginBottom = '10px';

    const defaultOption = document.createElement('option');
    defaultOption.value = '';
    defaultOption.textContent = defaultText;
    select.appendChild(defaultOption);

    options.forEach(optionText => {
        const option = document.createElement('option');
        option.value = optionText;
        option.textContent = optionText;
        select.appendChild(option);
    });

    return select;
}

function createInput(type, placeholder) {
    const input = document.createElement('input');
    input.type = type;
    input.placeholder = placeholder;
    input.classList.add('form-control', 'mr-2');
    input.style.flex = '1';
    input.style.minWidth = '150px';
    input.style.marginBottom = '10px';

    // バリデーションを追加
    if (type === 'number') {
        input.addEventListener('input', function() {
            if (input.value < 0) {
                input.setCustomValidity('個数は正の整数でなければなりません');
            } else {
                input.setCustomValidity('');
            }
        });
    }

    return input;
}

function createButton(text, className) {
    const button = document.createElement('button');
    button.textContent = text;
    button.classList.add(...className.split(' '));
    button.style.marginBottom = '10px';
    return button;
}

function fetchData(url, callback) {
    fetch(url)
        .then(response => {
            if (!response.ok) {
                return response.json().then(errorData => {
                    throw new Error(errorData.error || `HTTPエラー！ステータスコード: ${response.status}`);
                });
            }
            return response.json();
        })
        .then(data => callback(data))
        .catch(error => {
            logMessage(`データの取得中にエラーが発生しました: ${error}`);
            if (error.message.includes('サーバーが停止しています')) {
                alert('サーバーが停止しています。');
            } else if (error.message.includes('データベースが停止しています')) {
                alert('データベースが停止しています。');
            } else {
                alert(`データの取得中にエラーが発生しました: ${error.message}`);
            }
        });
}

function populateCategorySelect(categories) {
    const categorySelect = document.getElementById('category-select');
    categories.forEach(category => {
        const option = document.createElement('option');
        option.value = category.categoryName;
        option.textContent = category.categoryName;
        categorySelect.appendChild(option);
    });
}

function displayStockInfo(data) {
    const tableResponsive = document.createElement('div');
    tableResponsive.classList.add('table-responsive');

    const table = document.createElement('table');
    table.classList.add('table', 'table-bordered');

    const thead = document.createElement('thead');
    const tbody = document.createElement('tbody');

    const headerRow = document.createElement('tr');
    const headers = ['分類', '名称', '個数', '保管場所', '説明'];
    headers.forEach(headerText => {
        const th = document.createElement('th');
        th.textContent = headerText;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);

    data.forEach(stockInfo => {
        const row = document.createElement('tr');
        const cells = [
            stockInfo.categoryinfo.categoryName,
            stockInfo.name,
            stockInfo.amount,
            stockInfo.centerinfo.centerName,
            stockInfo.description
        ];
        cells.forEach(cellText => {
            const td = document.createElement('td');
            td.textContent = cellText;
            row.appendChild(td);
        });
        tbody.appendChild(row);
    });

    table.appendChild(thead);
    table.appendChild(tbody);
    stockList.appendChild(table);

    // 最初にすべての名称を表示
    allNames = data.map(stockInfo => stockInfo.name);
    populateNameSelect(allNames);
}

function populateNameSelect(names) {
    const nameSelect = document.getElementById('name-select');
    nameSelect.innerHTML = ''; // 既存のオプションをクリア
    const defaultNameOption = document.createElement('option');
    defaultNameOption.value = '';
    defaultNameOption.textContent = '名称を選択';
    nameSelect.appendChild(defaultNameOption);

    names.forEach(name => {
        const option = document.createElement('option');
        option.value = name;
        option.textContent = name;
        nameSelect.appendChild(option);
    });
}

function handleCategoryChange() {
    const selectedCategory = document.getElementById('category-select').value;
    if (selectedCategory) {
        fetchData(`${nameApiUrl}?category=${selectedCategory}`, names => {
            populateNameSelect(names.map(name => name.name));
        });
    } else {
        populateNameSelect(allNames);
    }
}

function handleSearch() {
    const selectedCategory = document.getElementById('category-select').value;
    const selectedName = document.getElementById('name-select').value;
    const amountInput = document.querySelector('input[type="number"]');
    const amount = amountInput.value ? parseInt(amountInput.value) : null;
    const amountCondition = document.getElementById('amount-condition-select').value;

    // バリデーションチェック
    if (amountInput.checkValidity() === false) {
        alert(amountInput.validationMessage);
        return;
    }

    let searchApiUrl = `${apiUrl}/search?`;

    if (selectedCategory) {
        searchApiUrl += `category=${selectedCategory}&`;
    }
    if (selectedName) {
        searchApiUrl += `name=${selectedName}&`;
    }
    if (amount !== null) {
        searchApiUrl += `amount=${amount}&`;
    }
    if (amountCondition) {
        searchApiUrl += `than=${amountCondition}&`;
    }

    searchApiUrl = searchApiUrl.slice(0, -1); // 最後の '&' を削除

    logMessage(searchApiUrl); // ここでURLをコンソールに出力

    fetchData(searchApiUrl, updateTable);
}

function updateTable(filteredData) {
    const tbody = document.querySelector('tbody');
    tbody.innerHTML = ''; // 既存の行をクリア
    filteredData.forEach(stockInfo => {
        const row = document.createElement('tr');
        const cells = [
            stockInfo.categoryinfo.categoryName,
            stockInfo.name,
            stockInfo.amount,
            stockInfo.centerinfo.centerName,
            stockInfo.description
        ];
        cells.forEach(cellText => {
            const td = document.createElement('td');
            td.textContent = cellText;
            row.appendChild(td);
        });
        tbody.appendChild(row);
    });
}

// 年を自動的に取得して表示
document.addEventListener('DOMContentLoaded', (event) => {
    const currentYear = new Date().getFullYear();
    document.querySelector('.copyright span').textContent = `Copyright © Your Website ${currentYear}`;
});