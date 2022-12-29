new Vue({
    el:'#searchform',
    data() {
        return {
            option:1,
            text:''
        }
    },
    methods: {
        doSearch() {
            axios.get('/search?option='+this.option+'&text='+this.text)
            .then(res=>{
                console.log(res);
                if(res.data["status"]==="1")
                    location.href=res.data["goto"];
                else {
                    alert(res.data["msg"]);
                }
            })
        }
    },
})