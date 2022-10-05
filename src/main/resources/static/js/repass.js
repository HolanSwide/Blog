new Vue({
    el:'#app',
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === "") {
              callback(new Error("请输入用户信息"));
            } else {
              if (this.ruleForm.checkPass !== "") {
                this.$refs.sendData.user.validateField("password");
              }
              callback();
            }
          };
          var validatePass2 = (rule, value, callback) => {
            if (value === "") {
              callback(new Error("请输入密码"));
            } else if (value.length < 8 || value.length > 13) {
              callback(new Error("长度必须在8-12之内!"));
            } else {
              callback();
            }
          };
        return {
            url:'http://localhost/pwd',
        loading:false,
        same:true,
        sendData:{
            phone:'',
            password:''
        },
        repass:''
    }
    },
    watch:{
        deep:true,
        repass(oldValue,newValue) {
            console.log(newValue);
            if(this.repass!==this.sendData.password){
                this.same=false;
            }
            else {
                this.same=true;
            }
        }
    },
    methods: {
        toRepass() {
            axios({
                method: 'patch',
                url: this.url,
                data: JSON.stringify(this.sendData),
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).then(res=>{
                if(res.data["sign"]===1) {
                    this.$message({
                        message:res.data["msg"]+" 3秒后跳转登录...",
                        type:'success',
                        showClose:true,
                        duration:3000
                    });
                    setTimeout(function (){
                        location.href='http://localhost/';
                    },3000)
                } else {
                    this.loading=false;
                    this.$message.error({
                        message:res.data["msg"],
                        showClose: true,
                        duration: 0
                    });
                }
            }).catch(err=>{
                console.log(err);
            })
        }
    }
})