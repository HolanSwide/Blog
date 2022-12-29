new Vue({
    el:'#user',
    data:{
        uid:'0000000',
        username:'用户名',
        sign:'用户签名',
        email:'test@email.com',
    },
    created() {
        axios
        .get('/me')
        .then(response=>{
            this.username=response.data["user"]["username"];
            this.uid=response.data["user"]["uid"];
            this.sign=response.data["info"]["sign"];
            this.email=response.data["info"]["email"];
        })
    },
    methods:{
        
    }
});
new Vue({
    el:'#essays',
    data:{
        essays:[
            {"fid":"abc", "uid":123, "title":"test", "summary":"testtttttt", "time":"--", "pid":"123"},
            {"fid":"sdf", "uid":456, "title":"test", "summary":"testtttttt", "time":"--", "pid":"111"}
        ],
        postInfo:{
            key:'auto',
            value:'',
            begin:'0',
            end:'10'
        }
    }
    ,
    created() {
        // 获取初始信息
        let decodeUrl = decodeURI(location.href);
        let getFromUrl = decodeUrl.slice(decodeUrl.indexOf('?') + 1);
        if (decodeUrl.includes("?")) {
            this.postInfo.value = getFromUrl;
            this.postInfo.key = 'title';
        } else {
            this.postInfo.value = 'auto';
        }
        axios({
            method: 'post',
            url: '/essay',
            data: JSON.stringify(this.postInfo),
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            }
        }).then(res=>{
            this.essays=res.data["data"];
        })
    }
})