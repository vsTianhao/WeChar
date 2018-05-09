/****
 * 注意引入jquery.js,jquery.qrcode.min.js,qrcode.js
 * 中文乱码解决
 * @param str 中文字符串
 * @returns 转码后字符串
 */
function utf16to8(str) {
	var out, i, len, c;
	out = "";
	len = str.length;
	for (i = 0; i < len; i++) {
		c = str.charCodeAt(i);
		if ((c >= 0x0001) && (c <= 0x007F)) {
			out += str.charAt(i);
		} else if (c > 0x07FF) {
			out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
			out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
			out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
		} else {
			out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
			out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
		}
	}
	return out;
}
/****
 * 生成二维码
 * @param id 标签id
 * @returns
 */
function createqrcode(id){
	jQuery(id).qrcode({
		width:256,
		height:256,
		correctLevel:0,
		colorDark : '#000000',//前景色
		colorLight : '#ffffff',//背景色
		text : utf16to8("http://my.oschina.net/superkangning"
		)
		/*
		容错级别，可设置为：
		QRCode.CorrectLevel.L
		QRCode.CorrectLevel.M
		QRCode.CorrectLevel.Q
		QRCode.CorrectLevel.H
		*/
	});
}
//删除,标签id
function clear(id){
	$(id).clear();
}
