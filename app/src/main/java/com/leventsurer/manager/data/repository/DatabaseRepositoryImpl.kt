package com.leventsurer.manager.data.repository

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.leventsurer.manager.data.model.*
import com.leventsurer.manager.tools.constants.FirebaseConstants.APARTMENT_COLLECTIONS
import com.leventsurer.manager.tools.constants.FirebaseConstants.CONCIERGE_ANNOUNCEMENT
import com.leventsurer.manager.tools.constants.FirebaseConstants.DUTIES
import com.leventsurer.manager.tools.constants.FirebaseConstants.FINANCIAL_EVENTS
import com.leventsurer.manager.tools.constants.FirebaseConstants.RESIDENT_REQUESTS
import com.leventsurer.manager.tools.constants.FirebaseConstants.USER_COLLECTION

import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val database: FirebaseFirestore,
) : DatabaseRepository {


    override suspend fun getConciergeAnnouncements(): Resource<ArrayList<ConciergeAnnouncementModel>> {
        return try {
            val announcements = arrayListOf<ConciergeAnnouncementModel>()
            val result: QuerySnapshot =
                database.collection(APARTMENT_COLLECTIONS).document("mrpLL3uhAi35Kl4lSohj")
                    .collection(
                        CONCIERGE_ANNOUNCEMENT
                    ).get().await()

            for (document in result) {
                announcements.add(document.toObject(ConciergeAnnouncementModel::class.java))
            }
            Resource.Success(announcements)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun getConciergeDuties(): Resource<ArrayList<ConciergeDutiesModel>> {
        return try {
            val duties = arrayListOf<ConciergeDutiesModel>()
            val result: QuerySnapshot =
                database.collection(APARTMENT_COLLECTIONS).document("mrpLL3uhAi35Kl4lSohj")
                    .collection(
                        DUTIES
                    ).get().await()

            for (document in result) {
                duties.add(document.toObject(ConciergeDutiesModel::class.java))
            }
            Resource.Success(duties)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun getRecentFinancialEvents(): Resource<ArrayList<FinancialEventModel>> {
        return try {
            val financialEvents = arrayListOf<FinancialEventModel>()
            val result: QuerySnapshot =
                database.collection(APARTMENT_COLLECTIONS).document("mrpLL3uhAi35Kl4lSohj")
                    .collection(
                        FINANCIAL_EVENTS
                    ).get().await()

            for (document in result) {
                financialEvents.add(document.toObject(FinancialEventModel::class.java))
            }
            Resource.Success(financialEvents)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun getResidentsRequests(): Resource<ArrayList<ResidentsRequestModel>> {
        return try {
            val requests = arrayListOf<ResidentsRequestModel>()
            val result: QuerySnapshot =
                database.collection(APARTMENT_COLLECTIONS).document("mrpLL3uhAi35Kl4lSohj")
                    .collection(
                        RESIDENT_REQUESTS
                    ).get().await()

            for (document in result) {
                requests.add(document.toObject(ResidentsRequestModel::class.java))
            }
            Resource.Success(requests)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun getUsers(): ArrayList<UserModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getAUser(fullName: String, doorNumber: String): UserModel {
        TODO()
    }

    override suspend fun addNewUser(
        name: String,
        apartmentCode: String,
        carPlate: String,
        doorNumber: String,
        role: String
    ) {
        val user = hashMapOf(
            "carPlate" to carPlate,
            "doorNumber" to doorNumber,
            "duesPaymentStatus" to false,
            "fullName" to name,
            "phoneNumber" to "",
            "role" to role
        )

        val apartmentsCollection:QuerySnapshot = database.collection(APARTMENT_COLLECTIONS).get().await()
        var documentPath = ""
        for(apartmentDocument:DocumentSnapshot in apartmentsCollection){
            if(apartmentDocument.data?.get("apartmentName") as String == apartmentCode){
                documentPath = apartmentDocument.reference.path
            }
        }
        database.document("/$documentPath").collection(USER_COLLECTION).add(user)

    }

    override suspend fun addNewApartment(
        name: String,
        apartmentCode: String,
        carPlate: String,
        doorNumber: String,
        role: String, apartmentName: String
    ) {
        database.collection(APARTMENT_COLLECTIONS).add(
            hashMapOf(
                "apartmentName" to apartmentName
            )
        )
        addNewUser(name, apartmentCode, carPlate, doorNumber, role)
    }
}