import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyAlertsComponent } from './my-alerts.component';

describe('MyAlertsComponent', () => {
  let component: MyAlertsComponent;
  let fixture: ComponentFixture<MyAlertsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MyAlertsComponent]
    });
    fixture = TestBed.createComponent(MyAlertsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
