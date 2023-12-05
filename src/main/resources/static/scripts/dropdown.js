function showDropdown(element) {
    var dropdownMenu = element.querySelector('.dropdown-menu');
    dropdownMenu.classList.add('show');
}

function hideDropdown(element) {
    var dropdownMenu = element.querySelector('.dropdown-menu');
    dropdownMenu.classList.remove('show');
}