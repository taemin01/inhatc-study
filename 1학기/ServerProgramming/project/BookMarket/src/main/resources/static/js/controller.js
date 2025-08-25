function addToCart(bookid) {
    if(confirm("장바구니에 도서를 추가합니까?") == true) {
        document.addForm.action="/cart/book/"+bookid;
        document.addForm.submit();
    }
}

function removeFromCart(bookid) {
    document.removeForm.action = "/cart/book/"+bookid;
    document.removeForm.submit();
    setTimeout('location.reload()', 10);
}

function clearCart(cartId) { // 장바구니에 저장된 모든 도서 항목을 삭제합니다.
    if (confirm("모든 도서를 장바구니에서 삭제합니까?") == true) {
        // document.clearForm.action = "cart/"+cartId //교안대로 해도 안 됩니다 교수님...
        document.clearForm.submit();
        setTimeout('location.reload()', 10);
    }
}

function deleteConfirm(id) {
    if (confirm("삭제 합니다!!") == true) location.href ="/books/delete?id="+id;
    else return;
}