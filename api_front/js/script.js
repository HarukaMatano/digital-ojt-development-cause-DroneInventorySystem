const apiUrl='http://localhost:8080/stock-info/active';
const categoryApiUrl = 'http://localhost:8080/category-info'; // 分類名を取得するAPIのURL

const stockList =document.getElementById('stock-list');
    
// 検索フォームの枠を生成
const searchContainer = document.createElement('div');
searchContainer.classList.add('search-container');
searchContainer.style.display = 'flex';
searchContainer.style.flexWrap = 'wrap';

// 分類名のプルダウンメニューを追加
const categorySelect = document.createElement('select');
categorySelect.id = 'category-select';
const defaultOption = document.createElement('option');
defaultOption.value = '';
defaultOption.textContent = '分類を選択';
categorySelect.classList.add('form-control', 'mr-2'); 
categorySelect.style.flex = '1';
categorySelect.style.minWidth = '150px';
categorySelect.style.marginBottom = '10px';
categorySelect.appendChild(defaultOption);
searchContainer.appendChild(categorySelect);

// 名称のプルダウンメニューを追加
const nameSelect = document.createElement('select');
nameSelect.id = 'name-select';
const defaultNameOption = document.createElement('option');
defaultNameOption.value = '';
defaultNameOption.textContent = '名称を選択';
nameSelect.classList.add('form-control', 'mr-2');
nameSelect.style.flex = '1';
nameSelect.style.minWidth = '150px';
nameSelect.style.marginBottom = '10px';
nameSelect.appendChild(defaultNameOption);
searchContainer.appendChild(nameSelect);

// 個数の入力フィールドを追加
const amountInput = document.createElement('input');
amountInput.type = 'number';
amountInput.placeholder = '個数を入力';
amountInput.classList.add('form-control', 'mr-2');
amountInput.style.flex = '1';
amountInput.style.minWidth = '150px';
amountInput.style.marginBottom = '10px';
searchContainer.appendChild(amountInput);

// 個数の以上以下のプルダウンメニューを追加
const amountConditionSelect = document.createElement('select');
const defaultConditionOption = document.createElement('option');
defaultConditionOption.value = '';
defaultConditionOption.textContent = '以上・以下を選択';
amountConditionSelect.appendChild(defaultConditionOption);
const conditionOptions = ['以上', '以下'];
conditionOptions.forEach(condition => {
    const option = document.createElement('option');
    option.value = condition;
    option.textContent = condition;
    amountConditionSelect.appendChild(option);
});
amountConditionSelect.classList.add('form-control', 'mr-2');
amountConditionSelect.style.flex = '1';
amountConditionSelect.style.minWidth = '150px';
amountConditionSelect.style.marginBottom = '10px';
searchContainer.appendChild(amountConditionSelect);

// 検索ボタンを追加
const searchButton = document.createElement('button');
searchButton.textContent = '検索';
searchButton.classList.add('btn','btn-primary');
searchButton.style.marginBottom = '10px';
searchContainer.appendChild(searchButton);

// 検索コンテナをstockListに追加
stockList.appendChild(searchContainer);
    
    
    
// 分類名をAPIから取得してプルダウンメニューに表示
fetch(categoryApiUrl)
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTPエラー！ステータスコード: ${response.status}`);
        }
        return response.json();
    })
    .then(categories => {
        categories.forEach(category => {
            const option = document.createElement('option');
            option.value = category.categoryName;
            option.textContent = category.categoryName;
            categorySelect.appendChild(option);
        });
    })
    .catch(error => {
        console.error('分類名の取得中にエラーが発生しました:', error);
    });

fetch(apiUrl)
	.then(response =>{
		if(!response.ok){
			throw new Error(`HTTPエラー！ステータスコード: ${response.status}`);
		}
		return response.json();
	})
	.then(data =>{
		
		const tableResponsive = document.createElement('div');
        tableResponsive.classList.add('table-responsive');
		
		const table=document.createElement('table');
		
		table.classList.add('table', 'table-bordered');
	
		const thead = document.createElement('thead');
		const tbody=document.createElement('tbody');
		
		const headerRow = document.createElement('tr');
		const headers = ['分類','名称','個数','保管場所','説明',];
		headers.forEach(headerText=>{
			const th = document.createElement('th');
			th.textContent=headerText;
			headerRow.appendChild(th);
		});
		thead.appendChild(headerRow);
		
		data.forEach(stockInfo=>{
			const row = document.createElement('tr');
			const cells =[
				stockInfo.categoryinfo.categoryName,
				stockInfo.name,
				stockInfo.amount,
				stockInfo.centerinfo.centerName,
				stockInfo.description
			];
			cells.forEach(cellText=>{
				const td = document.createElement('td');
				td.textContent=cellText;
				row.appendChild(td);
				});
				tbody.appendChild(row);
			});
			
			table.appendChild(thead);
			table.appendChild(tbody);
			stockList.appendChild(table);
						
			// 分類名が選択されたときに名称のプルダウンメニューを更新
        categorySelect.addEventListener('change', () => {
            const selectedCategory = categorySelect.value;
            const names = data
                .filter(stockInfo => stockInfo.categoryinfo.categoryName === selectedCategory)
                .map(stockInfo => stockInfo.name);
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
        });
        
         // 検索ボタン押下後の動作を追加
        searchButton.addEventListener('click', () => {
            const selectedCategory = categorySelect.value.toLowerCase();
            const selectedName = nameSelect.value.toLowerCase();
            const amount = amountInput.value ? parseInt(amountInput.value) : null;
            const amountCondition = amountConditionSelect.value;

            const filteredData = data.filter(stockInfo => {
                const categoryMatch = selectedCategory ? stockInfo.categoryinfo.categoryName.toLowerCase().includes(selectedCategory) : true;
                const nameMatch = selectedName ? stockInfo.name.toLowerCase().includes(selectedName) : true;
                const amountMatch = amount !== null ? (amountCondition === '以上' ? stockInfo.amount >= amount : stockInfo.amount <= amount) : true;
                return categoryMatch && nameMatch && amountMatch;
            });

            // テーブルを更新
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
        });
			
		})
		
		.catch(error => {
			console.error('データの取得中にエラーが発生しました:',error);
			categoryList.innerHTML='データ取得中にエラーが発生しました。';
		});
	