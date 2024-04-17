function toggleUserOptions() {
    var userOptions = document.getElementById("userOptions");
    userOptions.style.display = userOptions.style.display === "none" ? "block" : "none";
}

document.getElementById('expandBtn').addEventListener('click', function() {
    var additionalCategories = document.getElementById('additionalCategories');
    if (additionalCategories.style.display === 'none') {
        additionalCategories.style.display = 'block';
        this.textContent = 'Свернуть фильтр';
    } else {
        additionalCategories.style.display = 'none';
        this.textContent = 'Расширить фильтр';
    }
})