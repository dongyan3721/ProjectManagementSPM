import request from "@/utils/request";

export function querySelfLeadedCourses(query){
  return request({
    url: '/spm/teacher-guide/query',
    method: 'get',
    query: query
  })
}

