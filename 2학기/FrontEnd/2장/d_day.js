var now = new Date();
var before = new Date(2025, 12, 25);
var interval = now.getTime() - before.getTime();
// �и��� ������ �ð��� ��¥ ������ ��ȯ�Ѵ�.
interval = Math.floor(interval / (1000*60*60*24));
alert("D-Day : " + interval + "��");
