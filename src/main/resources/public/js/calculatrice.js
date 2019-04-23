var id_calcul = document.getElementById("field");

function f_calc(n)
{
	if(n=='ce')
	{
		id_calcul.value = "";
    }
	else if(n=='+')
	{
    	id_calcul.value += "+"; 
	}
	else if(n=='-')
	{
    	id_calcul.value += "-"; 
	}
	else if(n=='*')
	{
    	id_calcul.value += "*"; 
	}
	else if(n=='/')
	{
    	id_calcul.value += "/"; 
	}
	else if(n=='+-')
	{
    	id_calcul.value += "+-"; 
	}
	else if(n=='%')
	{
    	id_calcul.value += "%"; 
	}
	else if(n=='.')
	{
    	id_calcul.value += "."; 
	}
	else if(n=='nbs')
	{
    	id_calcul.value = id_calcul.value.substring(0, id_calcul.value.length - 1);
	}
}

function add_calc(n)
{
	id_calcul.value += n;  
}