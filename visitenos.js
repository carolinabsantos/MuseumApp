function myFunction() {
  var e = document.getElementById("tipodevisita");
  var strUser = e.options[e.selectedIndex].text;
  console.log(value);
  if (value == 'M') {
       x.style.display = "block";
  } else {
      mostraG()
  }
}

// $('#tipodevisita').on('change', function () {
//   let value = $(this).val()
//
//   if (value == 'M') {
//       mostraM()
//     } else {
//       mostraG()
//     }
//   });

function mostraM()) {
  document.getElementById("TMV").style.visibility="visible";
}

function mostraG() {
  document.getElementById("TMV").style.visibility="hidden";
}

function hideBoth()  {
      document.getElementById("TMV").style.visibility="hidden";
}
