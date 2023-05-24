import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicemenDashboardComponent } from './servicemen-dashboard.component';

describe('ServicemenDashboardComponent', () => {
  let component: ServicemenDashboardComponent;
  let fixture: ComponentFixture<ServicemenDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ServicemenDashboardComponent]
    });
    fixture = TestBed.createComponent(ServicemenDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
