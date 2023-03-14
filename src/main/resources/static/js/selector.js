// первый список активатора языков
const optionMenu = document.querySelector(".select-menu"),
    selectBtn = optionMenu.querySelector(".select-btn"),
    options = optionMenu.querySelectorAll(".option"),
    sBtn_text = optionMenu.querySelector(".sBtn-text");

selectBtn.addEventListener("click", () => optionMenu.classList.toggle("active"));

options.forEach(option => {
    option.addEventListener("click", () => {
        let selectedOption = option.querySelector(".option-text").innerText;
        sBtn_text.innerText = selectedOption;
        console.log(selectedOption)
        optionMenu.classList.remove("active");
    })
})
// сдесь второй список активатора языков 
const optionMenudooble = document.querySelector(".select-menu-dooble"),
    selectBtndooble = optionMenudooble.querySelector(".select-btn-dooble"),
    optionsdooble = optionMenudooble.querySelectorAll(".option-dooble"),
    sBtn_textdooble = optionMenudooble.querySelector(".sBtn-text-dooble");

selectBtndooble.addEventListener("click", () => optionMenudooble.classList.toggle("active"));

optionsdooble.forEach(option => {
    option.addEventListener("click", () => {
        let selectedOption = option.querySelector(".option-text-dooble").innerText;
        sBtn_textdooble.innerText = selectedOption;
        localStorage.setItem("language", selectedOption)
        console.log(selectedOption)
        optionMenudooble.classList.remove("active");
    })
})
// смена действий при активации смены фона по кнопке
var check = document.getElementById("checkbox-field");

check.onclick = () => {


    document.querySelector("body").classList.toggle('activery');
    document.querySelector(".reg").classList.toggle('activery');
    document.querySelector(".inputbox").classList.toggle('activery');
    document.querySelector(".inputbox-one").classList.toggle('activery');
    document.querySelector(".btn_btn-success").classList.toggle('activery');
    document.querySelector(".inputForm").classList.toggle('activery');


}
// сдесь код на форматирование поля при переключение строк
document.getElementById("russianWord").value = null;
document.getElementById("russianWord").onclick = function () {
    document.getElementById("botlihWord").value = null;
}
document.getElementById("botlihWord").onclick = function () {
    document.getElementById("russianWord").value = null;
}