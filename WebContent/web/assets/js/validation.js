	
function showWaiting()
	{
		 $("#waiting").modal('show');
	}	
function clear()
	{
   	 document.getElementById("successMessage").style.display="none";
	 document.getElementById("errorMessage").style.display="none";
	 document.getElementById('error').style.display="none";
	}
function validateArabicDescription(arabicValue)
{
	var text=arabicValue.value;
    var arregex = /[\u0600-\u06FF]/;
   if(arregex.test(text))
	   {
	   document.getElementById('arabicDescriptionMessage').innerHTML="";
	   return true;
	   }else
		{
		document.getElementById('arabicDescriptionMessage').innerHTML="You Should Enter Arabic Description ";
		document.getElementById('error').style.display="block";
       	document.getElementById('errorMessage').style.display="block";
		document.getElementById('errorMessage').innerHTML="You Should Enter Arabic Description";
		return false;
		 }

}
function validateEnglishName(englishValue,ID)
{
	var text=englishValue.value;
    var arregex =/^[-_ a-zA-Z0-9]+$/;
   if(arregex.test(text))
	   {
	   	 document.getElementById(ID).innerHTML="";
	     return true;
	   }else
		{
		 document.getElementById('error').style.display="block";
		 document.getElementById(ID).innerHTML="You Should Enter English Name  Description ";
		 document.getElementById('errorMessage').innerHTML=document.getElementById('errorMessage').innerHTML+"<br/>"+"Invalid Email Pattern...";
		 return false;
		 }
}
function ValidateEmailPattern(email)
{       
	var text=email.value;
	var arregex = /\S+@\S+\.\S+/;
		if(arregex.test(text))
			{
			document.getElementById('emailMessage').style.color="green";
			 document.getElementById('emailMessage').innerHTML="Valid Email";
			 return true;
			}else
			{
			document.getElementById('emailMessage').innerHTML="You Should Enter Valid Email  ";
			return false;
			}
}


function validatePhoneNumber(number,ID)
{
var numberValue=number.value;
var arregex =/^(\+91-|\+91|0)?\d{10}$/;
if(arregex.test(numberValue))
   {
	 document.getElementById(ID).innerHTML="";
	 return false;
   }else
	   {
	 document.getElementById(ID).innerHTML="Invalid Mobile Number Pattern ";
	 document.getElementById('error').style.display="block";
	 document.getElementById('errorMessage').innerHTML=document.getElementById('errorMessage').innerHTML+"<br/>"+"Invalid Mobile Number Pattern";
	 return true;
	   }

}
function validateNumber(number,ID)
{
	var numberValue=number.value;
	var arregex = /^(?=.)([+-]?([0-9]*)(\.([0-9]+))?)$/;
	if(arregex.test(numberValue))
	   {
		 document.getElementById(ID).innerHTML="";
		  return true;
	   }else
		   {
		 document.getElementById(ID).innerHTML="You Should Enter Numbers Digits 0.0 ";
		 document.getElementById('error').style.display="block";
	     document.getElementById('errorMessage').style.display="block";
	     document.getElementById('errorMessage').innerHTML=document.getElementById('errorMessage').innerHTML+"<br/>"+"You Should Enter Numbers Digits 0.0 ";
		 return false;
		   }
	
}
function validateEnglishDescription(englishValue)
{
	
	var text=englishValue.value;
    var arregex = /^[0-9a-zA-Z-_]+$/;
   if(arregex.test(text))
	   {
	   document.getElementById('englishDescriptionMessage').innerHTML="";
	   return true;
	   }else
		{
		 document.getElementById('englishDescriptionMessage').innerHTML="You Should Enter English  Description ";
		 document.getElementById('error').style.display="block";
	     document.getElementById('errorMessage').style.display="block";
	     document.getElementById('errorMessage').innerHTML=document.getElementById('errorMessage').innerHTML+"<br/>"+"You Should Enter English  Description ";
		 
		 return false;
		 }
	

	
	
}
function closeWaiting()
{
	 $("#waiting").modal('hide');
	 document.getElementById('errorMessage').innerHTML="";
}

function Success() {
	 document.getElementById("successMessage").style.display="none";
	 document.getElementById("errorMessage").style.display="none";
	 $("#waiting").modal('hide');
	 document.getElementById('errorMessage').innerHTML="";
}
function Fail() {
	 document.getElementById("successMessage").style.display="none";
	 document.getElementById("errorMessage").style.display="none";
	 $("#waiting").modal('hide');
	 document.getElementById('errorMessage').innerHTML="";
}
function showError(error)
{
	 
	var message= error.substring(23);
	 document.getElementById("successMessage").style.display="none";
	 document.getElementById('errorMessage').innerHTML=""+message;
	 setTimeout(Fail, 5000);
}
