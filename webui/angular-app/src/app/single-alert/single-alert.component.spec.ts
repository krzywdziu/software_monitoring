import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleAlertComponent } from './single-alert.component';

describe('SingleAlertComponent', () => {
  let component: SingleAlertComponent;
  let fixture: ComponentFixture<SingleAlertComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SingleAlertComponent]
    });
    fixture = TestBed.createComponent(SingleAlertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
