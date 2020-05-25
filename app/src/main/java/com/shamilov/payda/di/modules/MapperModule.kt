package com.shamilov.payda.di.modules

import com.shamilov.payda.data.mapper.DonationMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Shamilov on 25.05.2020
 */
@Module
class MapperModule {

    @Singleton
    @Provides
    fun provideDonationMapper(): DonationMapper {
        return DonationMapper()
    }
}