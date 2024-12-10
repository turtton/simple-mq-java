package net.turtton.simplemq.common.db;

import net.turtton.simplemq.common.model.CollectedData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CollectedDataRepository {
    Postgresql database;

    public CollectedDataRepository(Postgresql database) {
        this.database = database;
    }

    @Nullable
    public CollectedData getByIndex(int index) {
        try (var con = database.getConnection()) {
            var stmt = con.prepareStatement("SELECT * FROM results WHERE id = ?");
            stmt.setObject(1, index);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return new CollectedData(rs.getInt("id"), rs.getInt("result"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(@NotNull CollectedData data) {
        try (var con = database.getConnection()) {
            var stmt = con.prepareStatement("INSERT INTO results (id, result) VALUES (?, ?)");
            stmt.setObject(1, data.id());
            stmt.setInt(2, data.result());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(@NotNull CollectedData data) {
        try (var con = database.getConnection()) {
            var stmt = con.prepareStatement("UPDATE results SET result = ? WHERE id = ?");
            stmt.setInt(1, data.result());
            stmt.setObject(2, data.id());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(@NotNull UUID id) {
        try (var con = database.getConnection()) {
            var stmt = con.prepareStatement("DELETE FROM results WHERE id = ?");
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
