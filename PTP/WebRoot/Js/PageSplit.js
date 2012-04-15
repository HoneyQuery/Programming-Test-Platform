/**
 * 
 * @param  Num ÿҳ����¼��
 * @param  TableId ��Ҫ���Ƶ�table��id����
 * @param  TBodyId ��Ҫ���Ƶ�table��tbody ��id����
 * @Author Shawzt
 * @CreateTime 2012/3/17
 */

function Page(Num, TableId, TBodyId) {
	this.num = Num;
	this.tableId = TableId;
	this.tBodyId = TBodyId;
	this.pageIndex = 0; //ҳ����
	this.Table = null; //�������
	this.TBody = null; //Ҫ��ҳ����
	this.dataRows = 0; //��¼������
	this.rowCount = 0; //��¼��
	this.pageCount = 0; //ҳ��
	this.MyInit(); //��ʼ��;
};
/**
 ��ʼ��
 */
function MyInit() {
	this.Table = document.getElementById(this.tableId); //��ȡtable����
	this.TBody = this.Table.tBodies[this.tBodyId]; //��ȡtBody����
	this.dataRows = this.TBody.rows;
	this.rowCount = this.dataRows.length;
	try {
		/*����趨��ÿҳ�����ʾ�ļ�¼��num<=0 ��num=1
		 * ������num����Ҫ��ʾ����������rowCount��num=rowCount
		 * ����num��Ϊ�趨��ֵ
		 */
		this.num = (this.num <= 0) || (this.num > this.rowCount) ? this.rowCount
				: this.num;
		/*
		 * �����趨��ҳ��
		 * ��rowCount�ܱ�num����ʱ,pageCount=rowCount/num
		 * ����ҪpageCount=rowCount/num+1
		 */
		this.pageCount = parseInt(this.rowCount % this.num == 0 ? this.rowCount
				/ this.num : this.rowCount / this.num + 1);
	} catch (exception) {
	}
	this.updateTableRows();
};
/**
 ��һҳ
 */
function nextPage() {
	if (this.pageIndex + 1 < this.pageCount) {
		this.pageIndex += 1;
		this.updateTableRows();
	}
};
/**
 ��һҳ
 */
function prePage() {
	if (this.pageIndex >= 1) {
		this.pageIndex -= 1;
		this.updateTableRows();
	}
};
/**
 ��ҳ
 */
function firstPage() {
	if (this.pageIndex != 0) {
		this.pageIndex = 0;
		this.updateTableRows();
	}
};
/**
 βҳ
 */
function lastPage() {
	if (this.pageIndex + 1 != this.pageCount) {
		this.pageIndex = this.pageCount - 1;
		this.updateTableRows();
	}
};
/**
 ҳ��λ����
 */
function aimPage(iPageIndex) {
	if (iPageIndex > this.pageCount - 1) {
		this.pageIndex = this.pageCount - 1;
	} else if (iPageIndex < 0) {
		this.pageIndex = 0;
	} else {
		this.pageIndex = iPageIndex;
	}
	this.updateTableRows();
};
/**
 ִ�з�ҳʱ��������ʾ�������
 */
function updateTableRows() {
	var iCurrentRowCount = this.num * this.pageIndex;
	var iMoreRow = this.num + iCurrentRowCount > this.rowCount ? this.num
			+ iCurrentRowCount - this.rowCount : 0;
	var tempRows = this.cloneRows();
	var removedTBody = this.Table.removeChild(this.TBody);
	var newTBody = document.createElement("TBODY");
	newTBody.setAttribute("id", this.tBodyId);

	for ( var i = iCurrentRowCount; i < this.num + iCurrentRowCount - iMoreRow; i++) {
		newTBody.appendChild(tempRows[i]);
	}
	this.Table.appendChild(newTBody);
	/*
	 this.dataRowsΪthis.oTBody��һ�����ã�
	 �Ƴ�this.oTBody��ôthis.dataRows���ý���ʧ,
	 code:this.dataRows = tempRows;�ָ�ԭʼ�����м���.
	 */
	this.dataRows = tempRows;
	this.TBody = newTBody;
};
/**
 ��¡ԭʼ�����м���
 */
function cloneRows() {
	var tempRows = [];
	for ( var i = 0; i < this.dataRows.length; i++) {
		/*
		 code:this.dataRows[i].cloneNode(param), 
		 param = 1 or true:������ָ���ڵ㷢չ��ȥ�����нڵ�,
		 param = 0 or false:ֻ��ָ���Ľڵ���������Ա�����.
		 */
		tempRows[i] = this.dataRows[i].cloneNode(1);
	}
	return tempRows;
};