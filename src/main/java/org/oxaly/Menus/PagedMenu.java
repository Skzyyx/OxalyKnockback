package org.oxaly.Menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class PagedMenu<T> implements InventoryHolder {

    protected List<T> items;          // lista de cosméticos
    protected int currentPage;
    protected Inventory inventory;

    // Slots específicos para mostrar los items (tamaño fijo)
    protected final int[] displaySlots = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25};

    public PagedMenu(List<T> items) {
        this.items = items;
        this.currentPage = 0;
    }

    public void createInventory() {
        // Inventario 54 slots (puedes cambiar tamaño si quieres)
        inventory = Bukkit.createInventory(this, 45, getMenuTitle());

        int itemsPerPage = displaySlots.length;
        int start = currentPage * itemsPerPage;
        int end = Math.min(start + itemsPerPage, items.size());

        List<T> pageItems = items.subList(start, end);

        // Limpio inventario para evitar restos de páginas previas
        inventory.clear();

        // Poner items SOLO en slots indicados
        for (int i = 0; i < pageItems.size(); i++) {
            int slot = displaySlots[i];
            inventory.setItem(slot, toItemStack(pageItems.get(i)));
        }

        addNavigationButtons();

        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = glass.getItemMeta();
        meta.displayName(Component.text(""));
        glass.setItemMeta(meta);

        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, glass);
            }
        }
    }

    protected void addNavigationButtons() {
        // Botón previo en slot 45, siguiente en 53 (por ejemplo)
        if (currentPage > 0) {
            inventory.setItem(39, createNavigationItem(Material.ARROW, Component.text("Página Anterior")));
        }
        if ((currentPage + 1) * displaySlots.length < items.size()) {
            inventory.setItem(41, createNavigationItem(Material.ARROW, Component.text("Página Siguiente")));
        }
    }

    public void nextPage() {
        if ((currentPage + 1) * displaySlots.length < items.size()) {
            currentPage++;
            createInventory();
        }
    }

    public void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            createInventory();
        }
    }

    protected ItemStack createNavigationItem(Material material, Component name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        item.setItemMeta(meta);
        return item;
    }

    // Este metodo convierte el objeto T en un ItemStack para mostrar
    protected abstract ItemStack toItemStack(T item);

    protected abstract Component getMenuTitle();
}
