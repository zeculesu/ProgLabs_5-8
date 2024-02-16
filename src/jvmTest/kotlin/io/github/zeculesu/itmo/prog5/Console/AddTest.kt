package io.github.zeculesu.itmo.prog5.Console

import io.github.zeculesu.itmo.prog5.manager.command.AddCommand
import kotlin.test.Test

import io.github.zeculesu.itmo.prog5.data.*
import io.github.zeculesu.itmo.prog5.manager.CommandSetMapImpl
import io.github.zeculesu.itmo.prog5.manager.CommandIOMemoryImpl
import io.github.zeculesu.itmo.prog5.manager.ConsoleCommandEnvironmentTestImpl
import io.github.zeculesu.itmo.prog5.manager.command.ShowCommand
import kotlin.test.assertEquals

class AddTest {
    @Test
    fun testConsole() {
        val io = CommandIOMemoryImpl(
            listOf(
                "name",
                "4 5",
                "342",
                "SUPPRESSOR",
                "HEAVY_BOLTGUN",
                "CHAIN_AXE",
                "name parentLegion"
            )
        )
        val collection = SpaceMarineCollection()
        val comm = AddCommand()
        val comm_show = ShowCommand()
        val commandSet = CommandSetMapImpl(comm, comm_show)
        val env = ConsoleCommandEnvironmentTestImpl(commandSet)
        comm.execute(collection, io, env, arrayOf())
        assertEquals(1, collection.size())
        val el = collection.get_by_id(1)
        assertEquals("name", el.name)
        assertEquals(4, el.coordinates.x)
        assertEquals(5f, el.coordinates.y)
        assertEquals(342, el.health)
        assertEquals(AstartesCategory.SUPPRESSOR, el.category)
        assertEquals(Weapon.HEAVY_BOLTGUN, el.weaponType)
        assertEquals(MeleeWeapon.CHAIN_AXE, el.meleeWeapon)
        assertEquals("name", el.chapter.name)
        assertEquals("parentLegion", el.chapter.parentLegion)
    }
}