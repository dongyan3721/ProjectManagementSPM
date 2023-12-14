import request from "@/utils/request";

export function getDetailCourses(id) {
  return request({
    url: '/spm/student-courses/' + id,
    method: 'get'
  })
}
