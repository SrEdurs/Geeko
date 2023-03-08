function checkCuestionario(check) {

    if(check.classList.contains('checkbox-alias')){
      check.classList.remove("checkbox-alias");
      check.classList.add("checkCuestionario");
    }else{
      check.classList.remove("checkCuestionario");
      check.classList.add("checkbox-alias");
    }

  }