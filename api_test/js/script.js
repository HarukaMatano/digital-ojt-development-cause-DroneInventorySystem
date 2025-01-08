const apiUrl='http://localhost:8080/category-info';

const categoryList =document.getElementById('category-list');

fetch(apiUrl)
	.then(response =>{
		if(!response.ok){
			throw new Error(`HTTPエラー！ステータスコード: ${response.status}`);
		}
		return response.json();
	})
	.then(data =>{
		
		categoryList.innerHTML='';		
		
		const table=document.createElement('table');
		table.classList.add('category-table');
		const thead = document.createElement('thead');
		const tbody=document.createElement('tbody');
		
		const headerRow = document.createElement('tr');
		const headers = ['カテゴリーID','カテゴリー名','削除フラグ','作成日時','更新日時'];
		headers.forEach(headerText=>{
			const th = document.createElement('th');
			th.textContent=headerText;
			headerRow.appendChild(th);
		});
		thead.appendChild(headerRow);
		
		data.forEach(categoryInfo=>{
			const row = document.createElement('tr');
			const cells =[
				categoryInfo.categoryId,
				categoryInfo.categoryName,
				categoryInfo.deleteFlag,
				categoryInfo.createDate,
				categoryInfo.updateDate
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
			categoryList.appendChild(table);
		})
		.catch(error => {
			console.error('データの取得中にエラーが発生しました:',error);
			categoryList.innerHTML='データ取得中にエラーが発生しました。';
		});
	