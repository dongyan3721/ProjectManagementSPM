import request from "@/utils/request";

export function queryDetailedLeadCourseInfo(id){
  return request({
    url: '/spm/teacher-guide/' + id,
    method: 'get'
  })
}
