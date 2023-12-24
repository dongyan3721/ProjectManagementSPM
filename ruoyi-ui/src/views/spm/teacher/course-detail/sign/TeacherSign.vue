<template>
  <div class="app-container">
    <div class="up-signed">
      <h2>已签到</h2>
      <el-table :data="signed" v-loading="signLoading">
        <el-table-column label="学生学号" align="center" prop="studentId"/>
        <el-table-column label="学生姓名" align="center" prop="studentName"/>
        <el-table-column label="签到时间" align="center" prop="signTime"/>
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-button size="small" type="text" icon="el-icon-delete" @click="teacherDelete(scope.row)">删除点名记录</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="down-unsigned">
      <h2>未签到</h2>
      <el-table :data="unsigned" v-loading="unSignLoading">
        <el-table-column label="学生学号" align="center" prop="id"/>
        <el-table-column label="学生姓名" align="center" prop="name"/>
        <el-table-column label="联系方式" align="center" prop="phone"/>
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-button type="text" size="small" icon="el-icon-plus" @click="teacherHelp(scope.row)">手动签到</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import {deleteFakeSignRecord,
  pushNewStudentSignRecord,
  querySelfCourseStudentSelect,
  querySatisfiedSignRecord} from "@/api/spm/teacher-sign";
import {getCurrentTimeYYYYmmDDHHmmSS} from "@/utils/dongyan";
export default {
  name: 'TeacherSign',
  data(){
    return{
      signLoading: false,
      unSignLoading: false,
      signed: [],
      unsigned: [],
      courseId: this.$route.params.courseId,
      signId: this.$route.params.signIndex,
      // 记录是否允许启用watch
      prove: true
    }
  },
  methods:{
    teacherDelete(row){
      this.$modal.confirm(`确定删除${row.studentName}的签到记录吗？`).then(()=>{
        deleteFakeSignRecord(row.id).then(res=>{
          this.$modal.msgSuccess("删除成功！");
          this.unsigned = []
          this.signed = []
          this.prove = true;
          this.requestAllSelectStu();
          this.requestSignedStudent();
        })
      })
    },
    teacherHelp(row){
      this.$modal.confirm(`确定要为${row.name}签到吗？`).then(()=>{
        // console.log({studentId: row.id, studentName: row.name,
        //   signTime: getCurrentTimeYYYYmmDDHHmmSS(), signId: this.signId})
        pushNewStudentSignRecord({studentId: row.id, studentName: row.name,
          signTime: getCurrentTimeYYYYmmDDHHmmSS(), signId: this.signId}).then(res=>{
            this.$modal.msgSuccess("签到成功！")
          this.unsigned = []
          this.signed = []
          this.prove = true;
          this.requestAllSelectStu();
          this.requestSignedStudent();
        })
      })
    },
    requestAllSelectStu(){
      this.signLoading = true
      querySelfCourseStudentSelect(this.courseId).then(res=>{
        this.unsigned = res.rows
        console.log(this.unsigned)
        this.signLoading = false
      })
    },
    requestSignedStudent(){
      this.unSignLoading = true;
      querySatisfiedSignRecord({signId: this.signId}).then(res=>{
        this.signed = res.rows
        console.log(this.signed)
        this.unSignLoading = false
      })
    }
  },
  mounted() {
    document.querySelector(".body-main-tabs").style.display = "none"
  },
  watch:{
    signed(newValue, oldValue){
      if(this.unsigned.length!=0&&this.prove){
        let ids = [];
        for (let i = 0; i < this.signed.length; i++) {
          ids.push(this.signed[i])
        }
        let s = this.unsigned.map(item=>{
          function inList(item, list) {
            for(let i=0;i<list.length;++i){
              if(item==list[i]){
                return true;
              }
            }
            return false;
          }
          return inList(item.id, ids)?null:item
        })
        .filter(item=>{
          return item!==null
        })
        this.unsigned = s;
        this.prove = false;
        console.log("前")
        console.log(this.signed)
        console.log(this.unsigned)
      }
    },
    unsigned(newValue, oldValue){
      if(this.signed.length!=0&&this.prove){
        let ids = [];
        for (let i = 0; i < this.signed.length; i++) {
          ids.push(this.signed[i])
        }
        let s = this.unsigned.map(item=>{
          function inList(item, list) {
            for(let i=0;i<list.length;++i){
              if(item==list[i]){
                return true;
              }
            }
            return false;
          }
          return inList(item.id, ids)?null:item
        })
          .filter(item=>{
            return item!==null
          })
        this.unsigned = s;
        this.prove = false;
        console.log("后")
        console.log(this.signed)
        console.log(this.unsigned)
      }
    }
  },
  created() {
    this.requestSignedStudent();
    this.requestAllSelectStu();
  }

}
</script>

<style lang="scss">

</style>
