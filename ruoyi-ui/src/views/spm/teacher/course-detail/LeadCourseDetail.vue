<script>
import { queryDetailedLeadCourseInfo } from "@/api/spm/teacher-leadcoursedetail";
import { listChapter as listChapterAPI } from "@/api/spm/chapter";
import { listContent as listContentAPI } from "@/api/spm/content";

export default {
  name: "LeadCourseDetail",
  data() {
    return {
      activeName: 'chapter',
      courseInfo: {
        chapters: []  // Initialize chapters array
      },
    };
  },
  methods: {
    handleTabsClick(tab, ev) {
      console.log(tab, ev);
    },
    selectDetailedCourseInformation() {
      queryDetailedLeadCourseInfo(this.$route.params.courseId).then(res => {
        this.courseInfo = { ...res.data };
        this.fetchChapters();
        console.log(this.courseInfo);
    });
    },
    fetchChapters() {
      // Call the listChapter method from chapter.js to get the list of chapters
      listChapterAPI({ courseId: this.courseInfo.id }).then((chaptersRes) => {
        this.courseInfo.chapters = chaptersRes; // Assume the response structure is similar to the one used in the chapter.vue component
        this.courseInfo.chapters.forEach(chapter => {
          chapter.contents = []; // Initialize contents array for each chapter
        });
        this.fetchChapterContents();
      });
    },
    fetchChapterContents() {
      // For each chapter, call the listContent method from content.js to get the list of contents
      this.courseInfo.chapters.forEach(chapter => {
        listContentAPI({ captchaId: chapter.id }).then(contentsRes => {
          chapter.contents = contentsRes; // Assume the response structure is similar to the one used in the content.vue component
        });
      });
    },
    handleChapterSelect(chapterId) {
      // Handle the chapter selection event
      console.log('Selected chapter:', chapterId);
    },
    handleContentClick(content) {
      // Handle the content click event
      console.log('Clicked on content:', content);
    },
    created() {
      this.selectDetailedCourseInformation();
      document.body.style.cursor = "default";
    },
  }
};
</script>

<template>
  <div class="container-wrapper">
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleTabsClick" class="body-main-tabs">
      <el-tab-pane label="章节完善" name="chapter">
        <el-menu v-if="courseInfo.chapters" class="submenu-item" :default-active="activeChapter" mode="vertical" @select="handleChapterSelect">
          <el-menu-item v-for="chapter in courseInfo.chapters" :key="chapter.id" :index="chapter.id">
            {{ chapter.chapter_name }}
            <el-menu v-if="chapter.contents" class="submenu-item" mode="vertical">
              <el-menu-item v-for="content in chapter.contents" :key="content.id" :index="content.id" @click="handleContentClick(content)">
                {{ content.description }}
              </el-menu-item>
            </el-menu>
          </el-menu-item>
        </el-menu>
      </el-tab-pane>
      <el-tab-pane label="发布课程作业" name="homework">配置管理</el-tab-pane>
      <el-tab-pane label="点名" name="sign">角色管理</el-tab-pane>
      <el-tab-pane label="考试" name="exam">定时任务补偿</el-tab-pane>
      <el-tab-pane label="本班学生" name="classmates">本班学生</el-tab-pane>
    </el-tabs>
  </div>
</template>

<style scoped lang="scss">
// Your styles go here
</style>
