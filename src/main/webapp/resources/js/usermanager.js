function formatStatus(value,rows,index){
	if(rows.status==1){
		return "启用";  
	}else if(rows.status==0){  
        return "禁用";  
    }  
}