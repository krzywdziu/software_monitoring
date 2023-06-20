import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {
  getRowColor(severity: string): string {
    switch (severity) {
      case 'EMERGENCY':
        return 'emerg-severity';
      case 'ALERT':
        return 'alert-severity';
      case 'CRITICAL':
        return 'crit-severity';
      case 'ERROR':
        return 'err-severity';
      case 'WARNING':
        return 'warning-severity';
      case 'NOTICE':
        return 'notice-severity';
      case 'INFORMATIONAL':
        return 'info-severity';
      case 'DEBUG':
        return 'debug-severity';
      default:
        return '';
    }
  }

  formatDate(timestamp: string) {
    const date = new Date(timestamp);
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");
    const seconds = String(date.getSeconds()).padStart(2, "0")
    const day = String(date.getDate()).padStart(2, "0");
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const year = String(date.getFullYear());
    const formattedDate = `${day}-${month}-${year} ${hours}:${minutes}:${seconds}`;
    return formattedDate;
  }

}
