import { NgModule } from '@angular/core';
import { PostFilterPipe } from './post-filter.pipe';

@NgModule({
  declarations: [PostFilterPipe],
  exports: [PostFilterPipe]
})
export class PipesModule { }
