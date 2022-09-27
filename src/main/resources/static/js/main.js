new Vue({
    el:'#app',
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === '' || this.checkForm.oldPassword === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.checkForm.user.password !== '' ) {
                    this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (this.rePassword !== this.checkForm.user.password) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            allFiles:[],
            reAllFiles:[],
            keyAllFile:'',
            userList:[],
            reUserList:[],
            keyUser:'',
            manaDisabled:true,
            dialogVisible:false,
            diaData:{
                filename:'',
                url:''
            },
            userDiaData:{
                uid:'',
                isShow:false,
                isManaShow:false
            },
            files:[],
            refiles:[],
            loading:false,
            regCode:'',
            key:'',
            aside:1,
            fileList: [],
            sendData:{
                fileInfo:{
                    fid:'0',
                    url:'',
                    uid:'',
                    filename:'',
                    describe:'',
                    type:'',
                    memory:''
                }
            },
            userInfo:{
                uid:'',
                phone:'',
                email:'',
                sex:'',
                born:'',
                type:'3'
            },
            checkForm:{
                user:{
                    username:'',
                    password:''
                },
                oldPassword:''
            },
            rePassword:'',
            rules: {
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validatePass2, trigger: 'blur' }
                ],
            }
        };
    },
    created() {

        axios
            .get('http://localhost/user/me')
            .then(response => {
                    this.sendData.fileInfo.uid=response.data["uid"];
                    this.checkForm.user.username=response.data["username"];
                axios
                    .get('http://localhost/user/type?uid='+this.sendData.fileInfo.uid)
                    .then(response=>{
                        this.userInfo.type=response.data;
                        this.manaDisabled=this.userInfo.type > 2;
                        if(this.userInfo.type < 3) {
                            axios
                                .get('http://localhost/file/all')
                                .then(response=>{
                                    this.allFiles=response.data;
                                    this.reAllFiles=this.allFiles;
                                });
                            axios
                                .get('http://localhost/user/all')
                                .then(response=>{
                                    this.userList=response.data;
                                    for(let i=0;i<this.userList.length;i++) {
                                        if(this.userList[i].sex===1) this.userList[i].sex='男';
                                        else this.userList[i].sex='女';
                                        if(this.userList[i].type===0) this.userList[i].type='超级管理员';
                                        else if(this.userList[i].type===1) this.userList[i].type='管理员';
                                        else if(this.userList[i].type===2) this.userList[i].type='审核员';
                                        else if(this.userList[i].type===3) this.userList[i].type='用户';
                                    }
                                    this.reUserList=this.userList;
                                });
                        }
                    });
                }
            );
        axios
            .get('http://localhost/user/myinfo')
            .then(response=>{
                console. log(response.data);
                this.userInfo.uid=response.data["uid"];
                this.userInfo.born=response.data["born"];
                this.userInfo.phone=response.data["phone"];
                this.userInfo.email=response.data["email"];
                this.userInfo.sex=response.data["sex"];
                axios
                    .get('http://localhost/file/myfile?uid='+this.userInfo.uid)
                    .then(response=>{
                        console.log(response.data);
                        this.files = response.data;
                        this.refiles = response.data;
                    });
            });
    },
    watch:{
        key:{
            immediate:true,
            handler(oldName,newName){
                console.log(newName)
                this.files = this.refiles.filter((p)=>{
                    return p.filename.indexOf(this.key) !== -1 || p.describe.indexOf(this.key) !== -1
                })
            }
        },
        keyAllFile: {
            immediate:true,
            handler(oldName,newName){
                console.log(newName)
                this.allFiles = this.reAllFiles.filter((p)=>{
                    return p.filename.indexOf(this.keyAllFile) !== -1 || p.describe.indexOf(this.keyAllFile) !== -1
                })
            }
        },
        keyUser:{
            immediate:true,
            handler(oldName,newName){
                console.log(newName)
                this.userList = this.reUserList.filter((p)=>{
                    return p.uid===this.keyUser ||
                        p.type.indexOf(this.keyUser) !== -1 ||
                        p.username.indexOf(this.keyUser) !== -1 ||
                        p.phone.indexOf(this.keyUser) !== -1 ||
                        p.email.indexOf(this.keyUser) !== -1 ||
                        p.born.indexOf(this.keyUser) !== -1 ||
                        p.sex.indexOf(this.keyUser) !== -1;
                })
            }
        },
    },
    methods: {
            doManaUser(row, column, cell, event) {
                this.userDiaData.isManaShow=true;
                this.userDiaData.uid=row.uid;
            },
            upTo(type) {
                axios
                    .get('http://localhost/user/uptype?type='+type+'&uid='+this.userDiaData.uid)
                    .then(response=>{
                        this.$message({
                            message:'操作成功',
                            type:'success',
                            showClose:true,
                            duration:0
                        });
                        this.userDiaData.isManaShow=false;
                        axios
                            .get('http://localhost/user/all')
                            .then(response=>{
                                this.userList=response.data;
                                for(let i=0;i<this.userList.length;i++) {
                                    if(this.userList[i].sex===1) this.userList[i].sex='男';
                                    else this.userList[i].sex='女';
                                    if(this.userList[i].type===0) this.userList[i].type='超级管理员';
                                    else if(this.userList[i].type===1) this.userList[i].type='管理员';
                                    else if(this.userList[i].type===2) this.userList[i].type='审核员';
                                    else if(this.userList[i].type===3) this.userList[i].type='用户';
                                }
                                this.reUserList=this.userList;
                            });
                    });
            },
            doUser(row, column, cell, event) {
                this.userDiaData.isShow=true;
                this.userDiaData.uid=row.uid;
            },
            deleteUser() {
                axios
                    .get('http://localhost/user/del/?uid='+this.userDiaData.uid)
                    .then(response=>{
                        this.$message({
                            message:'删除成功',
                            type:'success',
                            showClose:true,
                            duration:0
                        });
                        this.userDiaData.isShow=false;
                        axios
                            .get('http://localhost/user/all')
                            .then(response=>{
                                this.userList=response.data;
                                for(let i=0;i<this.userList.length;i++) {
                                    if(this.userList[i].sex===1) this.userList[i].sex='男';
                                    else this.userList[i].sex='女';
                                    if(this.userList[i].type===0) this.userList[i].type='超级管理员';
                                    else if(this.userList[i].type===1) this.userList[i].type='管理员';
                                    else if(this.userList[i].type===2) this.userList[i].type='审核员';
                                    else if(this.userList[i].type===3) this.userList[i].type='用户';
                                }
                                this.reUserList=this.userList;
                            });
                    })

            },
            doFile(row, column, cell, event) {
                console.log(row);
                this.dialogVisible=true;
                this.diaData.filename=row.filename;
                this.diaData.url=row.url;
            },
            downloadFile() {
                window.open(this.diaData.url);
            },
            deleteFile() {
                axios({
                    method: 'post',
                    url: 'http://localhost/file/del',
                    data: JSON.stringify(this.diaData),
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                }).then(res=>{
                    if (res.data==="1") {
                        this.$message({
                            message:'删除成功',
                            type:'success',
                            showClose:true,
                            duration:0
                        });
                    }
                });
                this.dialogVisible=false;
                location.reload();
            },
        deleteOtherFile() {
            axios({
                method: 'post',
                url: 'http://localhost/file/del',
                data: JSON.stringify(this.diaData),
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).then(res=>{
                this.$message({
                    message:'删除成功',
                    type:'success',
                    showClose:true,
                    duration:0
                });
            });
            this.dialogVisible=false;
            axios
                .get('http://localhost/file/all')
                .then(response=>{
                    this.allFiles=response.data;
                    this.reAllFiles=this.allFiles;
                });
        },
            repass() {
            },
            handleBeforeUpload(file) {
                console.log(file)
                this.sendData.fileInfo.filename = file.name;
                this.sendData.fileInfo.memory = file.size;
                this.sendData.fileInfo.type = file.name.split(".")[1];
            },
            submitUpload() {
                this.loading=true;
                this.$refs.upload.submit();
            },
            handleSuccess(response,file,fileList) {
                this.loading=false;
                this.sendData.fileInfo.url=response;
                axios({
                    method: 'post',
                    url: 'http://localhost/file/details',
                    data: JSON.stringify(this.sendData),
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                }).then(res=>{
                    console.log(res);
                    this.$message({
                        message:res.data["msg"],
                        type:'success',
                        showClose:true,
                        duration:0
                    });
                }).catch(err=> {
                    console.log(err);
                    this.$message({
                        message: "error",
                        type: 'error',
                        showClose: true,
                        duration: 0
                    })
                });
                location.reload();
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleSelect(key, keyPath) {
               this.aside=key;
            },
        toExit() {
                this.loading=true;
            window.location.href='http://localhost/user/exit';
        },
        handleClose(){
            this.userDiaData.isShow=false;
            this.userDiaData.isManaShow=false;
        }
    }
});

