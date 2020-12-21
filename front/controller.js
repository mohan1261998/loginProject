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
    var url='https://jsonplaceholder.typicode.com/todos/1';
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
    document.getElementById("emailToReset").value ='';
    document.getElementById("forgotSuccess").innerHTML = "Your password is sent to your email id.";
}
 function forgotPassword(){
     
     window.location= './forgot';
 }

function goToLoginPage(){
    window.location= './';
}
function logout(){
    window.location= './';
}


