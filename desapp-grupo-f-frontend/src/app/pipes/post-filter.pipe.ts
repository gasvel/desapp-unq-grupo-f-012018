import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'postFilter'
})
export class PostFilterPipe implements PipeTransform {

  transform(posts: any[], searchText: String): any[] {
    if(!posts) return [];
    if(!searchText) return posts;
    searchText = searchText.toLowerCase();
    var postFilterDescription =  posts.filter( post => {
      return post.description.toLowerCase().includes(searchText);
    });
    var postFilterTitle =  posts.filter( post => {
      return post.title.toLowerCase().includes(searchText);
   });
   var postTotal = postFilterDescription.concat(postFilterTitle);
   return postTotal.filter(function(post, index, self){
     return index === self.indexOf(post);
   })
  }

}
