<script>
import {querySelectedCourses} from "@/api/spm/student-mycourses";
export default {
  name: "MyCourses",
  data(){
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 9,
        courseName: null,
        teacherName: null
      },
      selectedCourses: [],
      contentLength: 0,
      networkPrefix: 'http://localhost:8080',
      layOnElement: null
    }
  },
  methods: {
    getSelectedCourses(){
      querySelectedCourses({}).then(res=>{
        this.selectedCourses = res.rows;
        this.contentLength = res.rows.length
        this.mountCourseImageAndLeadTeacher();
      })
    },
    mountCourseImageAndLeadTeacher(){
      let cells = document.getElementsByClassName("cell-left-pic");
      let rightCells = document.getElementsByClassName("cell-right-text");
      for (let i=0;i<this.contentLength;++i){
        let createdImg = document.createElement("img")
        createdImg.src = this.networkPrefix+this.selectedCourses[i].courseCoverLink;
        createdImg.alt = "课程图片";
        cells[i].appendChild(createdImg);
        cells[i].addEventListener("mouseenter", (ev)=>{
          document.body.style.cursor = "pointer"
        })
        cells[i].addEventListener("mouseleave", (ev)=>{
          document.body.style.cursor = "default"
        })
        rightCells[i].addEventListener("mouseenter", (ev)=>{
          document.body.style.cursor = "pointer"
        })
        rightCells[i].addEventListener("mouseleave", (ev)=>{
          document.body.style.cursor = "default"
        })
        let teacherName = document.createElement("p")
        teacherName.innerHTML = "授课教师："+this.selectedCourses[i].courseTeacherName;
        let courseName = document.createElement("h2");
        courseName.innerHTML = this.selectedCourses[i].courseName;
        courseName.className = "p-head"
        let schoolName = document.createElement("p")
        schoolName.innerHTML = "理塘大学"
        let courseId = document.createElement("p")
        courseId.innerHTML = this.selectedCourses[i].id
        courseId.style.display = "none"
        courseId.classList.add("none-display-course-id")
        rightCells[i].appendChild(courseName)
        rightCells[i].appendChild(teacherName)
        rightCells[i].appendChild(schoolName)
        rightCells[i].appendChild(courseId)
        console.log("success")
      }
    },
    handleMouseEnter(ev){
      // document.body.style.cursor = "pointer"
      this.layOnElement = ev.target
    },
    handleCourseClick(ev){
      let targetCourseId = this.layOnElement.getElementsByClassName("none-display-course-id")[0].innerHTML;
      this.$router.push(`/spm/study-detail/index/${targetCourseId}`);
    }
  },
  created() {
    this.getSelectedCourses();
  }
}
</script>

<template>
  <div class="page-container">
    <div class="all-courses-body">
      <el-row :gutter="20" class="all-courses-line">
        <el-col :span="8" class="all-courses-cell">
          <div class="grid-content bg-purple" @click="handleCourseClick" v-on:mouseenter="handleMouseEnter" >
            <div class="cell-left-pic">

            </div>
            <div class="cell-right-text">

            </div>
          </div>
        </el-col>
        <el-col :span="8" class="all-courses-cell"><div class="grid-content bg-purple" @click="handleCourseClick" v-on:mouseenter="handleMouseEnter" ><div class="cell-left-pic"></div><div class="cell-right-text"></div></div></el-col>
        <el-col :span="8" class="all-courses-cell"><div class="grid-content bg-purple" @click="handleCourseClick" v-on:mouseenter="handleMouseEnter" ><div class="cell-left-pic"></div><div class="cell-right-text"></div></div></el-col>
      </el-row>
      <el-row :gutter="20" class="all-courses-line">
        <el-col :span="8" class="all-courses-cell"><div class="grid-content bg-purple" @click="handleCourseClick" v-on:mouseenter="handleMouseEnter" ><div class="cell-left-pic"></div><div class="cell-right-text"></div></div></el-col>
        <el-col :span="8" class="all-courses-cell"><div class="grid-content bg-purple" @click="handleCourseClick" v-on:mouseenter="handleMouseEnter" ><div class="cell-left-pic"></div><div class="cell-right-text"></div></div></el-col>
        <el-col :span="8" class="all-courses-cell"><div class="grid-content bg-purple" @click="handleCourseClick" v-on:mouseenter="handleMouseEnter" ><div class="cell-left-pic"></div><div class="cell-right-text"></div></div></el-col>
      </el-row>
      <el-row :gutter="20" class="all-courses-line">
        <el-col :span="8" class="all-courses-cell"><div class="grid-content bg-purple" @click="handleCourseClick" v-on:mouseenter="handleMouseEnter" ><div class="cell-left-pic"></div><div class="cell-right-text"></div></div></el-col>
        <el-col :span="8" class="all-courses-cell"><div class="grid-content bg-purple" @click="handleCourseClick" v-on:mouseenter="handleMouseEnter" ><div class="cell-left-pic"></div><div class="cell-right-text"></div></div></el-col>
        <el-col :span="8" class="all-courses-cell"><div class="grid-content bg-purple" @click="handleCourseClick" v-on:mouseenter="handleMouseEnter" ><div class="cell-left-pic"></div><div class="cell-right-text"></div></div></el-col>
      </el-row>
    </div>
  </div>
</template>

<style scoped lang="scss">
.grid-content {
  border-radius: 4px;
  min-height: 36px;
  height: 100%;
  position: relative;
  display: flex;
}
.bg-purple{
  //background-color: mediumpurple;
}
.all-courses-body{
  width: 100%;
  height: 80%;
}
.all-courses-cell{
  height: 100%;
}
.all-courses-line{
  height: 220px;
  padding: 10px 20px 10px 20px;
}
.page-container{
  height: 100%;
  width: 100%;
}
.cell-left-pic{
  width: 65%;
  height: 100%;
  //background-color: #5a5e66;
}
.cell-right-text{
  width: 35%;
  height: 100%;
  //background-color: #1ab394;
}
::v-deep .cell-left-pic img{
  width: 100%;
  height: 100%;
  border-radius: 20px;
}
::v-deep .cell-right-text p{
  color: dimgray;
  font-size: 14px;
  text-align: center;
}

::v-deep .cell-right-text .p-head{
  color: black;
  font-size: 20px;
  text-align: center;
  font-weight: bold;
  padding: 20px 0 0 0;
}
</style>
