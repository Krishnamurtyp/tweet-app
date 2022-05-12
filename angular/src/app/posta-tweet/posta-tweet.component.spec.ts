import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostaTweetComponent } from './posta-tweet.component';

describe('PostaTweetComponent', () => {
  let component: PostaTweetComponent;
  let fixture: ComponentFixture<PostaTweetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostaTweetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostaTweetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
