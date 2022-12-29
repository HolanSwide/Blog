new Vue({
    el:'#contact',
    data:{
        username:'shinji',
        info:{
            uid:'1000000',
            phone:'10112345432',
            sex:'男',
            email:'test@test.com',
            birth:'2002-07-27',
            address:'四川 成都',
            sign:''
        }
    },
    created() {
        // get user
        axios
        .get('/me')
        .then(response=>{
            this.username=response.data["user"]["username"];
            this.info=response.data["info"];
        })
    },
    methods: {
        subInfo() {
            axios({
                method:'put',
                url: '/info',
                data: JSON.stringify(this.info),
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).then(res=>{
                alert(res.data["msg"]);
            })
        }
    },
})