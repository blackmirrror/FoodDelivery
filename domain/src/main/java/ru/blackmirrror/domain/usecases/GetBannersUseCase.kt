package ru.blackmirrror.domain.usecases

import ru.blackmirrror.domain.R
import ru.blackmirrror.domain.models.Banner

class GetBannersUseCase {
    operator fun invoke(): List<Banner> {
        val banners = ArrayList<Banner>()
        banners.add(
            Banner(
                idBanner = 1,
                image = R.drawable.banner1
            )
        )
        banners.add(
            Banner(
                idBanner = 2,
                image = R.drawable.banner2
            )
        )
        return banners
    }
}