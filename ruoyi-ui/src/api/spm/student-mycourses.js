import request from "@/utils/request";

export function querySelectedCourses(data) {
  return request({
    url: '/spm/student-courses/query',
    method: 'post',
    data: data
  })
}
