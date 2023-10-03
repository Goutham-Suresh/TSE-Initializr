$('select[multiple]').mousedown(e=>{
e.target.selected = !e.target.selected;
e.stopPropagation();
return false;
})