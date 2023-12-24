import request from '@/utils/request'

// 查询课程章节内容列表
export function listContent(query) {
  return request({
    url: '/spm/content/list',
    method: 'get',
    params: query
  })
}

// 查询课程章节内容详细
export function getContent(id) {
  return request({
    url: '/spm/content/' + id,
    method: 'get'
  })
}

// 新增课程章节内容
export function addContent(data) {
  return request({
    url: '/spm/content',
    method: 'post',
    data: data
  })
}

// 修改课程章节内容
export function updateContent(data) {
  return request({
    url: '/spm/content',
    method: 'put',
    data: data
  })
}

// 删除课程章节内容
export function delContent(id) {
  return request({
    url: '/spm/content/' + id,
    method: 'delete'
  })
}
