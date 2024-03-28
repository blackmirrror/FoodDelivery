package ru.blackmirrror.fooddelivery.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.blackmirrror.fooddelivery.domain.models.Category

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo("idCategory") var idCategory: String = "",
    @ColumnInfo("strCategory") var strCategory: String? = null,
    @ColumnInfo("strCategoryThumb") var strCategoryThumb: String? = null,
    @ColumnInfo("strCategoryDescription") var strCategoryDescription: String? = null
) {
    companion object {
        fun fromResponseToEntity(response: Category): CategoryEntity {
            return CategoryEntity(
                idCategory = response.idCategory,
                strCategory = response.strCategory,
                strCategoryThumb = response.strCategoryThumb,
                strCategoryDescription = response.strCategoryDescription
            )
        }

        fun fromEntityToResponse(entity: CategoryEntity): Category {
            return Category(
                idCategory = entity.idCategory,
                strCategory = entity.strCategory,
                strCategoryThumb = entity.strCategoryThumb,
                strCategoryDescription = entity.strCategoryDescription,
                isClicked = false
            )
        }
    }
}