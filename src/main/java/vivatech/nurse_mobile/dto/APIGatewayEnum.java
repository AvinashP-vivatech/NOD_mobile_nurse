package vivatech.nurse_mobile.dto;

public class APIGatewayEnum {

    public enum ResponseStatus {
        SUCCESS, FAILED, NOCONTENT
    }
    public enum UserType {
        Patient, Doctor, Clinic, Lab, Subadmin, Superadmin, Translator_Nurse
    }
    public enum State {
        A, I
    }
    public enum IsFrom {
        Registration,Login
    }
    public enum YesNoEnum {
        Yes, No
    }
    public enum TransferStatus {
        YES, NO
    }

    public enum PackageType {
        Paid, Free
    }
    public enum OfferType {
        PAID, FREE
    }

    public enum PackageStatus {
        A, I
    }
    public enum DurationType {
        Monthly, Daily
    }
    public enum DurationStatus {
        A, I
    }
    public enum Status {
        A, I
    }
    public enum IsFeatured {
        TRUE, FALSE // 0,1 In Existing
    }
    public enum OrderStatus {
        Pending, Inprogress, Completed, Cancelled, Failed
      }
    public enum Gender {
        Male, Female
    }
    public enum Language {
        Somali, English
    }
    public enum ApproverStatus {
        Pending, Inprogress, Completed, Cancelled, pandding
    }
    public enum ConsultType {
        NOD, VIDEO, SELF_MNG, HOME_CARE, CLINIC_VISIT
    }
    public enum ConsultationStatus {
        Pending, Approve, Reject
    }
    public enum FeeType {
        chat, call, telephone, visit, visit_home
    }
    public enum CommissionType {
        percentage, cost
    }
    public enum DiscountType {
        PERCENTAGE, FIXAMOUNT
    }
    public enum RequestType {
        Inprocess, Pending, Book, Cancel, Complete, Failed
    }
    public enum ConsultationType {
        Paid, Free
    }
    public enum ConsultStatus {
        pending, completed
    }
    public enum AddedType {
        Doctor, Patient, Lab
    }
    public enum CouponCategory {
        HEALTHTIP, CONSULTATION, LAB_CONSULTATION
    }
    public enum CategoryStatus {
        Active, Inactive
    }
    public enum LabItemType{
        LAB_TEST, LAB_PACKAGE
    }
    public enum SupportTicketStatus {
        Open, Reopen, Close
    }
    public enum LabReportRequestStatus {
        Pending,
        Requested,
        Approved,
        Denied
    }
    public enum LabReportPaymentStatus {
        Pending,
        Requested,
        Completed,
        Rejected
    }
    public enum StatusType {
        ZERO, ONE
    }
    public enum NotificationType {
       Healthtip, Lab, Nod, Ambulance, Support, Consult
    }
    public enum NotificationFlag {
        Yes, No
    }
    public enum MedicalHistoryFlags {
        Y, N
    }
    public enum ConsultTypes {
        chat, video, telephone, visit
    }
    public enum ReportRequestStatus {
        Pending,
        Requested,
        Approved,
        Denied
    }

    public enum PaymentStatus {
        Pending,
        Requested,
        Completed,
        Rejected
    }
    public enum Classification{
        from_hospital, individual
    }
    public enum AdminDebitCredit{
        credit, debit
    }
    public enum NODPaymentStatus {
        Pending,
        Inprogress,
        Completed,
        Cancelled,
        Failed,
        Refunded,
        PartialRefunded
    }

    public enum NODOrderStatus {
        Pending,
        Inprogress,
        Completed,
        Cancelled
    }

    public enum NODTransferStatus {
        NO,
        YES,
        PENDING
    }
    public enum ServiceState {
        Initiated,
        Processing,
        Booked,
        Completed,
        Cancelled,
        Expired,
        Onway,
        Arrived
    }

    public enum CancelBy {
        Patient,
        Nurse
    }

    public enum DeviceEnvironment {
        Mobile,
        Web,
        USSD
    }
    public enum ConfirmAcknowledgment {
        ZERO,
        ONE
    }
    public enum NODServiceState {
        Initiated, Processing, Booked, Completed, Cancelled, Expired, Onway, Arrived
    }

    public enum NODCancelBy {
        Patient, Nurse
    }

}
