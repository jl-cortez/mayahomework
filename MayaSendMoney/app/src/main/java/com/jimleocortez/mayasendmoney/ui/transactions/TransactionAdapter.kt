package com.jimleocortez.mayasendmoney.ui.transactions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jimleocortez.mayasendmoney.R
import com.jimleocortez.mayasendmoney.network.model.Transaction

class TransactionAdapter(private val context: Context, private val transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTransactionId: TextView = itemView.findViewById(R.id.tvTransactionId)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.tvTransactionId.text = "${transaction.transactionId}"
        holder.tvAmount.text = formatAmount(context, transaction.amount)
        if(transaction.transactionType == "credit") {
            holder.tvAmount.setTextColor(context.getColor(android.R.color.holo_red_dark))
        }
    }

    override fun getItemCount(): Int = transactions.size

    fun formatAmount(context: Context, amount: Double): String {
        return context.resources.getString(R.string.amount_format, String.format("%.2f", amount))
    }
}
