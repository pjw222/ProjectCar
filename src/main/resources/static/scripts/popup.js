function movePopup() {
  window.location.href = '/product/brand/hyundai';
}
function closePopup(event) {
  event.stopPropagation(); // 이벤트 전파 중지
  var popup = document.getElementById('floatingPopup');
  popup.style.display = 'none';
}

