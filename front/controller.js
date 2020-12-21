function login(){

    var email = document.getElementById('emailId').value;
    console.log("email= ",email);
    if(email == "mohan@gmail.in"){
        
        document.getElementById("loginFailed").innerHTML = "Password or email is incorrect, Please try again";
        return;
    }

    if(!emailValidation(email)){
        document.getElementById("emailValidation").innerHTML = "Please enter a valid email";
        return ;
    }
    var xmlHttp = new XMLHttpRequest();
    var url='http://localhost:8080/login';
    xmlHttp.open( "GET", url, false ); 
    xmlHttp.send( null );
    console.log(xmlHttp.responseText);

    if (xmlHttp.status == 200){ 
            window.location= './home';
            return;
    }
    return false;
   
}

function emailValidation(email){
    if (/^([.a-zA-Z0-9_-]+)@([a-zA-Z0-9_-]+[.])+([a-zA-Z]{2,15})$/.test(email)) {
          return true;
      }else{
          return false;
           }
 }

function forgotFunction(){
    console.log("forgotFunction called");

    var xmlHttp = new XMLHttpRequest();
    var url='http://localhost:8080/forgotPassword';
    xmlHttp.open( "GET", url, false ); 
    xmlHttp.send( null );
    console.log(xmlHttp.responseText);

    if (xmlHttp.status == 200){ 
        document.getElementById("emailToReset").value ='';
        document.getElementById("forgotSuccess").innerHTML = "Your password is sent to your email id.";
        return;
    }
}
 function forgotPassword(){
     
     window.location= './forgot';
 }

function goToLoginPage(){
    window.location= './';
}
function logout(){

    var xmlHttp = new XMLHttpRequest();
    var url='http://localhost:8080/logout';
    xmlHttp.open( "GET", url, false ); 
    xmlHttp.send( null );
    console.log(xmlHttp.responseText);

    if (xmlHttp.status == 200){ 
        window.location= './';
        return;
    }
}


